package com.lyj.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lyj.myapplication.R;
import com.lyj.myapplication.activity.chat.ChatMainActivity;
import com.lyj.myapplication.util.Constants;
import com.lyj.myapplication.util.ThreadUtils;
import com.lyj.myapplication.util.ToastUtils;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void btn_login(View view) {
        final String userName = etAccount.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            etAccount.setError("账号不能为空！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("密码不能为空!");
            return;
        }

        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建链接配置对象
                    //ConnectionConfiguration(String serviceName,ProxyInfo proxy)
                    ConnectionConfiguration config = new ConnectionConfiguration(Constants.HOST, Constants.PORT);
                    //额外配置，方便开发，上线改回即可
                    config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);//禁止使用安全模式，明文传输，方便调试
                    config.setDebuggerEnabled(true);//开启调试模式，方便我们查看具体发送的内容

                    //创建链接对象
                    XMPPConnection conn = new XMPPConnection(config);
                    //开始链接
                    conn.connect();
                    conn.login(userName, password);
                    //已经登录成功
                    ToastUtils.ShowToast(LoginActivity.this, "登录成功");
                    finish();
                    Intent intent = new Intent(LoginActivity.this, ChatMainActivity.class);
                    startActivity(intent);
                } catch (XMPPException e) {
                    e.printStackTrace();
                    ToastUtils.ShowToast(LoginActivity.this, "登录失败");

                }
            }
        });

    }

}
