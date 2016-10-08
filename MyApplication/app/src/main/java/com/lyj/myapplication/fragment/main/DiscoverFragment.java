package com.lyj.myapplication.fragment.main;

import android.view.View;

import com.lyj.myapplication.R;
import com.lyj.myapplication.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lyj on 2016/10/1.
 */

public class DiscoverFragment extends BaseFragment {
    private Unbinder mUnbinder;
    @Override
    public View onCreateView() {
        View discoverView = View.inflate(context, R.layout.fragment_discover, null);
        mUnbinder = ButterKnife.bind(this, discoverView);
        return discoverView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
