package com.lyj.myapplication.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lyj.myapplication.R;
import com.lyj.myapplication.provider.UserProvider;
import com.lyj.myapplication.util.ThreadUtils;
import com.lyj.myapplication.util.ToastUtils;
import com.lyj.myapplication.view.ToggleView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initData();
        initVeiw();
    }

    private void initVeiw() {
       ToggleView tog_view = (ToggleView) findViewById(R.id.tog_view);
        tog_view.setSwitchDrawableBackgroundResource(R.mipmap.switch_background);
        tog_view.setSwitchDrawableSlideMenuResource(R.mipmap.slide_button);
        tog_view.setSwichState(false);
    }

    private void initData() {
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                values.put(UserProvider.UserTableMeta.USER_ACCOUNT,"zhangsan");
                values.put(UserProvider.UserTableMeta.USER_NICKNAME,"zs");
                values.put(UserProvider.UserTableMeta.USER_AVATAR,"0");
                values.put(UserProvider.UserTableMeta.USER_PINYIN,"zs");
                getContentResolver().insert(UserProvider.URI_MATCH,values);
            }
        });

        ThreadUtils.runInUIThread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = getContentResolver().query(UserProvider.URI_MATCH, null, null, null, null);
                while (cursor.moveToNext()){
                    String id = cursor.getString(cursor.getColumnIndex("_id"));
                    ToastUtils.ShowToast(TestActivity.this,id);
                }
            }
        });
    }
}
