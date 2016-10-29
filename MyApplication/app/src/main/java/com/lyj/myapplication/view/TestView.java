package com.lyj.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.lyj.myapplication.R;

/**
 * Created by lyj on 2016/10/26.
 */

public class TestView extends LinearLayout {
    ToggleButton tb;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.test_view_layout, this);
        initView();
    }

    private void initView() {
        tb = (ToggleButton) findViewById(R.id.tb);
    }

    public ToggleButton getView() {
        return tb;
    }
}
