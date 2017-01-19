package com.sodino.titlebar.fragment;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/1/19.
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class BaseFragment extends Fragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && isFixTransparentStatusBar()) {
            fixTransparentStatusBar(view);
        }

        super.onViewCreated(view, savedInstanceState);
    }


    /**
     * 是否需要改变status bar背景色，对于某些机型手机（如oppo）无法改变状态栏字体颜色，
     * 会被当前状态栏挡住字体颜色，因此修改透明状态栏背景色
     * @return true: 调用fixTransparentStatusBar()
     */
    protected boolean isFixTransparentStatusBar(){
        return false;
    }

    /**
     * @param view {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}中返回的view．
     * */
    protected void fixTransparentStatusBar(View view) {

    }
}
