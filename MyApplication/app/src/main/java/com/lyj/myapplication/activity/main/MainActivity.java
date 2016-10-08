package com.lyj.myapplication.activity.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.lyj.myapplication.R;
import com.lyj.myapplication.base.BaseActivity;
import com.lyj.myapplication.bean.TabBean;
import com.lyj.myapplication.fragment.main.DiscoverFragment;
import com.lyj.myapplication.fragment.main.HomeFragment;
import com.lyj.myapplication.fragment.main.MeFragment;
import com.lyj.myapplication.view.FragmentTabHost;
import com.lyj.myapplication.view.SlideMenu;
import com.lyj.myapplication.view.TopViewBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FragmentTabHost mTabHost;
    private List<TabBean> tabs;
    private LayoutInflater mInflater;
    private SlideMenu sm;
    @BindView(R.id.top_main_bar)
    TopViewBar mTopViewBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mInflater = getLayoutInflater().from(this);
        initView();
    }

    private void initView() {
        sm = (SlideMenu) findViewById(R.id.sm);
        mTopViewBar.setOnLeftClickListener(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        initTab();
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setCurrentTab(0);
    }

    @Override
    public void onClick(View v) {
        sm.switchMenu();
    }

    private void initTab() {
        TabBean homeTab = new TabBean(R.string.tab_tweet, R.drawable.widget_bar_tweet_selector, HomeFragment.class);
        TabBean dicoverTab = new TabBean(R.string.tab_discover, R.drawable.widget_bar_explore_selector, DiscoverFragment.class);
        TabBean meTab = new TabBean(R.string.tab_me, R.drawable.widget_bar_me_selector, MeFragment.class);
        if (tabs == null) {
            tabs = new ArrayList<>();
        }
        tabs.add(homeTab);
        tabs.add(dicoverTab);
        tabs.add(meTab);
        for (TabBean tabBean : tabs) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tabBean.getTitle())).setIndicator(buildIndicator(tabBean));
            mTabHost.addTab(tabSpec, tabBean.getFragment(), null);
        }
    }

    private View buildIndicator(TabBean tabBean) {
        View indicatorView = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView imageView = (ImageView) indicatorView.findViewById(R.id.tab_icon);
        TextView textView = (TextView) indicatorView.findViewById(R.id.tab_title);
        imageView.setImageResource(tabBean.getIcon());
        textView.setText(tabBean.getTitle());
        return indicatorView;
    }
}
