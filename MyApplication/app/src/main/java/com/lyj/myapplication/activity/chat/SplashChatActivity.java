package com.lyj.myapplication.activity.chat;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lyj.myapplication.R;
import com.lyj.myapplication.activity.LoginActivity;
import com.lyj.myapplication.util.ThreadUtils;

public class SplashChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                //停留3秒
                SystemClock.sleep(3000);
                //进入登录界面
                Intent intent = new Intent(SplashChatActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
