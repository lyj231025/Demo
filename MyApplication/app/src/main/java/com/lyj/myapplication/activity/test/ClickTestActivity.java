package com.lyj.myapplication.activity.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyj.myapplication.R;
import com.lyj.myapplication.util.ToastUtils;
import com.lyj.myapplication.view.TestView;

public class ClickTestActivity extends AppCompatActivity {
    LinearLayout llyout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_test);
        initView();
    }

    private void initView() {
        llyout = (LinearLayout) findViewById(R.id.ll_layout);
        for(int i=0;i<10;i++){

            if(i== 5){
                TextView tvs = new TextView(this);
                llyout.addView(tvs);
            } else {
                TestView tv = new TestView(this);
                llyout.addView(tv);
            }
        }
        for(int i=0;i<llyout.getChildCount();i++){
            if(llyout.getChildAt(i) instanceof  TestView){
                TestView tvs = (TestView) llyout.getChildAt(i);
                tvs.getView().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            ToastUtils.ShowToast(ClickTestActivity.this,"打开");
                        } else {
                            ToastUtils.ShowToast(ClickTestActivity.this,"关闭");
                        }
                    }
                });
            }

        }
    }
}
