package com.lyj.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lyj on 2016/10/31.
 */

public class ToggleView extends View {
    Paint paint;
    Bitmap switchBackground, slideButton;
    boolean flag = false;

    public ToggleView(Context context) {
        this(context, null);
    }

    public ToggleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(switchBackground.getWidth(), switchBackground.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(switchBackground, 0, 0, paint);
        if (flag) {
            int currX = switchBackground.getWidth() - slideButton.getWidth();
            canvas.drawBitmap(slideButton, currX, 0, paint);
        } else {
            canvas.drawBitmap(slideButton, 0, 0, paint);
        }

    }

    private void init() {
        paint = new Paint();
    }

    public void setSwitchDrawableBackgroundResource(int switch_background) {
        switchBackground = BitmapFactory.decodeResource(getResources(), switch_background);
    }

    public void setSwitchDrawableSlideMenuResource(int slide_button) {
        slideButton = BitmapFactory.decodeResource(getResources(), slide_button);
    }

    public void setSwichState(boolean b) {
        flag = b;
    }
}
