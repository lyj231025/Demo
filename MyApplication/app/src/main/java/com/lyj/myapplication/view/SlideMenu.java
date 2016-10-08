package com.lyj.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * Created by lyj on 2016/10/6.
 */

public class SlideMenu extends ViewGroup {
    private float downX;
    private float downY;
    private float moveX;


    private static final int MAIN_STATE = 0;
    private static final int MENU_STATE = 1;
    private int currentState = MAIN_STATE;
    private Scroller scroller;


    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMenu(Context context) {
        this(context, null);
    }

    private void init() {
        scroller = new Scroller(getContext());
    }

    /**
     * 设置View的宽高
     *
     * @param widthMeasureSpec  控件宽度
     * @param heightMeasureSpec 控件高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //左侧面板重新测量
        View leftMenu = getChildAt(0);
        int leftMenuWidth = leftMenu.getLayoutParams().width;
//        Toast.makeText(getContext(),leftMenuWidth+"",Toast.LENGTH_SHORT).show();
        leftMenu.measure(leftMenuWidth, heightMeasureSpec);
        //主面板重新测量
        View contentView = getChildAt(1);
        contentView.measure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 当前控件的大小以及位置
     *
     * @param changed 大小、位置是否改变
     * @param l       左侧距离
     * @param t       上边距
     * @param r       右侧距离（距离左侧边缘）
     * @param b       下边距（距离顶部边缘）
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //左侧面板的位置
        View leftMenu = getChildAt(0);
        leftMenu.layout(-leftMenu.getMeasuredWidth(), 0, 0, b);
        //主面板的位置
        View contentView = getChildAt(1);
        contentView.layout(l, t, r, b);
    }

    /**
     * 处理触摸事件
     *
     * @param event
     * @return scrollTo(int x, int y) or scrollBy(int x,int y)区别是：
     * scrollTo直接跳转到当前位置，scrollBy有一个移动的过程。
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
//                scrollTo(int x,int y);
                downX = event.getX();//按下时的当前位置的X轴坐标
                break;
            case MotionEvent.ACTION_MOVE://移动
                moveX = event.getX();//移动到某个位置的X轴坐标
                int scrollX = (int) (downX - moveX);//偏移量
                //判断是否越界，从而是否生效执行；不超出就执行，超出就固定位左边距
                int newScrollPosition = getScrollX() + scrollX;//移动后的点的位置+偏移量
                if (newScrollPosition < -getChildAt(0).getMeasuredWidth()) {//限定左边界
                    scrollTo(-getChildAt(0).getMeasuredWidth(), 0);
                } else if (newScrollPosition > 0) {//限定有边界
                    scrollTo(0, 0);
                } else {
                    scrollBy(scrollX, 0);//偏移量生效
                }
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP://抬起
                //设置滑动动画效果，根据左侧面板移动位置的一半进行比较
                int leftCenter = (int) (-getChildAt(0).getMeasuredWidth() / 2.0f);
                if (getScrollX() < leftCenter) {//侧面板打开
                    currentState = MENU_STATE;
                    updateCurrentView();
                } else {//侧面板关闭
                    currentState = MAIN_STATE;
                    updateCurrentView();
                }
                break;

        }
//        return super.onTouchEvent(event);
        return true;
    }

    /**
     * 执行打开/关闭动画
     */
    private void updateCurrentView() {
        int startX = getScrollX();//开始位置
        int dx = 0; //偏移量
        //加载动画，平滑滚动
        if (currentState == MENU_STATE) {//左侧打开
//            scrollTo(-getChildAt(0).getMeasuredWidth(), 0);
            dx = -getChildAt(0).getMeasuredWidth() - startX;
        } else {//左侧关闭
//            scrollTo(0, 0);
            dx = 0 - startX;
        }
//        scroller.startScroll(int startX, int startY, int dx, int dy, int duration);
        /**
         *  @param startX X轴开始值
         * @param startY  Y轴开始值
         * @param dx  X轴偏移量  结束位置-开始位置
         * @param dy  Y轴偏移量
         * @param duration 时间长度
         */
        //1111开始平滑的数据模拟
        int duration = Math.abs(dx * 5);//绝对值都是正的
        scroller.startScroll(startX, 0, dx, 0, duration);
        //模拟滚动值
        //-200 --> -240
        //-201 ---> -202 -->-207.....--->-240
        invalidate();//重绘机制  --> drawChild()-->computeScroll()
    }

    //2222维持动画继续
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            //true,动画没结束
            int currX = scroller.getCurrX();
            scrollTo(currX, 0);
            invalidate();//重绘界面 -->与上面循环调用
        }
    }

    //添加打开/关闭事件
    public void open() {
        currentState = MENU_STATE;
        updateCurrentView();
    }

    public void close() {
        currentState = MAIN_STATE;
        updateCurrentView();
    }

    public void switchMenu() {
        if (currentState == MAIN_STATE) {//默认显示主面板
            open();
        } else {
            close();
        }
    }

    /**
     * 事件拦截判断
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float xOffset = Math.abs(ev.getX()-downX);
                float yOffset = Math.abs(ev.getY()-downY);
                if(xOffset >yOffset && xOffset>5){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
