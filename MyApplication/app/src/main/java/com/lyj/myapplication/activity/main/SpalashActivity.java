package com.lyj.myapplication.activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lyj.myapplication.R;
import com.lyj.myapplication.activity.chat.SplashChatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpalashActivity extends AppCompatActivity {
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void btn_login() {
        startActivity(new Intent(this,SplashChatActivity.class));
    }
}
