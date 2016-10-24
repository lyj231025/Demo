package com.lyj.myapplication.util;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyj.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyj on 2016/10/10.
 */

public class ToolBarUtils {
     List<TextView> mTextViewList = new ArrayList<>();
    public  void createToolBar(LinearLayout mainBottom, String[] strs, int[] imgs){
        for(int i=0;i<strs.length;i++){
            TextView btnView = (TextView) View.inflate(mainBottom.getContext(), R.layout.tool_btn,null);
            btnView.setText(strs[i]);
            btnView.setCompoundDrawablesWithIntrinsicBounds(0,imgs[i],0,0);
            int width = 0;
            int height = LinearLayout.LayoutParams.MATCH_PARENT;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
            params.weight = 1;
            mainBottom.addView(btnView,params);
            mTextViewList.add(btnView);
            final int finalI = i;
            btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //调用接口方法
                    mOnTabClickListener.onTabClick(finalI);
                }
            });
        }
    }

    public void changeColor(int position){
        for(TextView tv:mTextViewList){
            tv.setSelected(false);
        }
        mTextViewList.get(position).setSelected(true);
    }
    //1、创建接口和接口方法
    public interface  OnTabClickListener{
        void onTabClick(int position);
    }
    //2、定义接口变量
    private OnTabClickListener mOnTabClickListener;
    //4、暴露公共方法
    public void setOnTabClickListener(OnTabClickListener onTabClickListener){
        this.mOnTabClickListener =onTabClickListener;
    }
}
