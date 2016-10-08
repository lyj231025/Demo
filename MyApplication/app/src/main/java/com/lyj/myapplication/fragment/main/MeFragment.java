package com.lyj.myapplication.fragment.main;

import android.view.View;

import com.lyj.myapplication.R;
import com.lyj.myapplication.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lyj on 2016/10/1.
 */

public class MeFragment extends BaseFragment {
    private Unbinder mUnbinder;
    @Override
    public View onCreateView() {
        View meView = View.inflate(context, R.layout.fragment_me, null);
        mUnbinder = ButterKnife.bind(this,meView);
        return meView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
