package com.lyj.myapplication.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.lyj.myapplication.dbhelper.ContactOpenHelper;

/**
 * Created by lyj on 2016/10/23.
 */

public class ContactsProvider extends ContentProvider {
    //反射得到类的完整路径--主机地址的常量
    private static final String AUTHORITIES = ContactsProvider.class.getCanonicalName();
    //Uri常量
    private static final Uri URI_CONTACT = Uri.parse("content://" + AUTHORITIES + "/contact");
    //地址匹配
    static UriMatcher uriMatcher;
    private static final int CONTACT = 1;
    ContactOpenHelper mOpenHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);//默认的
        //添加匹配规则
        uriMatcher.addURI(AUTHORITIES, "/contact", CONTACT);
        //content://com.lyj.myapplication.provider.ContactsProvider/contact-------->contact表
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new ContactOpenHelper(getContext());
        if (mOpenHelper != null) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //数据存到sqlite--》创建db文件，建立表
        int code = uriMatcher.match(uri);
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mOpenHelper.getWritableDatabase();
                long id = db.insert(ContactOpenHelper.T_CONTACT, "", values);
                if (id != -1) {//如果是-1，插入失败
                    //获得最新uri
                    //content://com.lyj.myapplication.provider.ContactsProvider/contact/id-->表中某一条数据
                    uri = ContentUris.withAppendedId(uri, id);
                    System.out.println(">>>>>>>插入成功>>>>>>>");
                }
                break;
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int code = uriMatcher.match(uri);
        int deleteCount = 0;
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mOpenHelper.getWritableDatabase();
                //删除的行数
                deleteCount = db.delete(ContactOpenHelper.T_CONTACT, selection, selectionArgs);
                if (deleteCount > 0) {
                    //删除成功
                    System.out.println(">>>>>>>删除成功>>>>>>>");
                }
                break;
        }
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int code = uriMatcher.match(uri);
        int updateCount = 0;
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mOpenHelper.getWritableDatabase();
                updateCount = db.update(ContactOpenHelper.T_CONTACT, values, selection, selectionArgs);
                if (updateCount > 0) {
                    //修改成功
                    System.out.println(">>>>>>>修改成功>>>>>>>");
                }
                break;
        }
        return updateCount;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int code = uriMatcher.match(uri);
        Cursor cusor = null;
        switch (code) {
            case CONTACT:
                SQLiteDatabase db = mOpenHelper.getWritableDatabase();
                cusor = db.query(ContactOpenHelper.T_CONTACT, projection, selection, selectionArgs, null, null, sortOrder);
                System.out.println(">>>>>>>查询成功>>>>>>>");
        }
        return cusor;
    }
}
