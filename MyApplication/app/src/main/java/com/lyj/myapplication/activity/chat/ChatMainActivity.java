package com.lyj.myapplication.activity.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyj.myapplication.R;
import com.lyj.myapplication.base.BaseActivity;
import com.lyj.myapplication.fragment.chat.ContactsFragment;
import com.lyj.myapplication.fragment.chat.SessionFragment;
import com.lyj.myapplication.util.ToolBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatMainActivity extends BaseActivity {
    @BindView(R.id.main_tv_title)
    TextView mMainTvTitle;
    @BindView(R.id.main_viewpager)
    ViewPager mMainViewpager;
    @BindView(R.id.main_bottom)
    LinearLayout mMainBottom;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ToolBarUtils mToolBarUtils;
    private String[] mStrs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);
        ButterKnife.bind(this);
        initData();
        initViewPagerListener();
    }



    private void initData() {
        //viewpager->view->pagerAdapter
        //viewpager->fragment->fragmentPagerAdapter
        //viewpager->fragment->fragmentStatePagerAdapter
        mFragmentList.add(new SessionFragment());
        mFragmentList.add(new ContactsFragment());
        mMainViewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mToolBarUtils = new ToolBarUtils();
        int[] imgs = {R.drawable.icon_meassage_selector,R.drawable.icon_selfinfo_selector};
        mStrs = new String[]{"会话","消息"};
        mToolBarUtils.createToolBar(mMainBottom, mStrs,imgs);
        mToolBarUtils.changeColor(0);
        mToolBarUtils.setOnTabClickListener(new ToolBarUtils.OnTabClickListener() {
            @Override
            public void onTabClick(int position) {
                mMainViewpager.setCurrentItem(position);
            }
        });
    }
    private void initViewPagerListener() {
        mMainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mMainTvTitle.setText(mStrs[position]);
                mToolBarUtils.changeColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }



}
