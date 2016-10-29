package com.lyj.myapplication.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

import com.lyj.myapplication.dbHelper.UserHelper;

/**
 * Created by lyj on 2016/10/29.
 */

public class UserProvider extends ContentProvider {
    public static final String AUTHORITIES = "com.lyj.myapplication.provider.UserProvider";

    public static final String DABASE_NAME = "user.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "users";
    private static final int INDICATOR = 1;
    private UserHelper mUserHelper;
    public static final Uri URI_MATCH = Uri.parse("content://" + AUTHORITIES + "/users");
    private static final UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITIES, "users", INDICATOR);
    }

    public static final class UserTableMeta implements BaseColumns {
        public static final String USER_ACCOUNT = "account";
        public static final String USER_NICKNAME = "nickname";
        public static final String USER_PINYIN = "pinyin";
        public static final String USER_AVATAR = "avatar";
    }

    @Override
    public boolean onCreate() {
        mUserHelper = new UserHelper(getContext());
        if (mUserHelper != null) {
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
        int code = mUriMatcher.match(uri);
        switch (code) {
            case INDICATOR:
                SQLiteDatabase db = mUserHelper.getWritableDatabase();
                long id = db.insert(UserProvider.TABLE_NAME, "", values);
                if (id > 0) {
                    uri = ContentUris.withAppendedId(uri, id);
                }
                break;
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        int code = mUriMatcher.match(uri);
        switch (code){
            case INDICATOR:
                SQLiteDatabase db = mUserHelper.getWritableDatabase();
                cursor = db.query(UserProvider.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }
        return cursor;
    }
}
