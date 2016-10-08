package com.lyj.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lyj.myapplication.R;

/**
 * Created by lyj on 2016/10/6.
 */

public class TopViewBar extends RelativeLayout {
    private ImageButton ibBack;
    private TextView title;
    public TopViewBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.top_normal_bar, this);
        initView();
    }

    public TopViewBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopViewBar(Context context) {
        this(context, null);
    }

    private void initView() {
        ibBack = (ImageButton) findViewById(R.id.ib_back);
        title = (TextView) findViewById(R.id.tv_title);
    }

    public void setOnLeftClickListener(OnClickListener mOnClickListener){
        ibBack.setOnClickListener(mOnClickListener);
    }

    public void setTitle(String str){
        title.setText(str);
    }
    public void setTitle(int str){
        title.setText(str);
    }
}
