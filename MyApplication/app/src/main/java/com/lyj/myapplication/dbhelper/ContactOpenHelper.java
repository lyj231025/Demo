package com.lyj.myapplication.dbhelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by lyj on 2016/10/23.
 */

public class ContactOpenHelper extends SQLiteOpenHelper {
    public static final String T_CONTACT = "t_contact";

    public class ContactTable implements BaseColumns {
        public static final String ACCOUNT = "account";
        public static final String NICKNAME = "nickname";
        public static final String AVATAR = "avatar";
        public static final String PINYIN = "pinyin";
    }

    public ContactOpenHelper(Context context) {
        super(context, "contact.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //表结构（_id,account,nickname,avatar,pinyin）
        String sql = "CREATE TABLE " + T_CONTACT + "(_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ContactTable.ACCOUNT + "TEXT, " +
                ContactTable.NICKNAME + "TEXT, " +
                ContactTable.AVATAR + "TEXT, " +
                ContactTable.PINYIN + "TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
