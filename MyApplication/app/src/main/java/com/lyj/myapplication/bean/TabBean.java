package com.lyj.myapplication.bean;

import android.widget.ImageView;

/**
 * Created by lyj on 2016/10/1.
 */

public class TabBean {
    private int title;
    private int icon;
    private Class<?> fragment;

    public TabBean(int title, int icon, Class<?> fragment) {
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class<?> getFragment() {
        return fragment;
    }

    public void setFragment(Class<?> fragment) {
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }
}
