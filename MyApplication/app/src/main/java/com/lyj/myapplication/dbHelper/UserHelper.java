package com.lyj.myapplication.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lyj.myapplication.provider.UserProvider;

/**
 * Created by lyj on 2016/10/29.
 */

public class UserHelper extends SQLiteOpenHelper {
    public UserHelper(Context context) {
        super(context, UserProvider.DABASE_NAME, null, UserProvider.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + UserProvider.TABLE_NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserProvider.UserTableMeta.USER_ACCOUNT + " TEXT," +
                UserProvider.UserTableMeta.USER_NICKNAME + " TEXT," +
                UserProvider.UserTableMeta.USER_AVATAR + " TEXT," +
                UserProvider.UserTableMeta.USER_PINYIN + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
