package com.sodino.titlebar.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sodino.titlebar.DeviceUtil;
import com.sodino.titlebar.R;
import com.sodino.titlebar.StatusbarColorUtils;

/**
 * Created by Administrator on 2017/1/13.
 */

public class BaseActivity extends Activity {
    protected View rootView;
    protected View viewStatusbarBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View v = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(v);
    }

    @Override
    public void setContentView(View view) {
        rootView = view;
        super.setContentView(view);

        if (isFixTransparentStatusBar()) {
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // 亮色模式,避免系统状态栏的图标不可见
                    visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                window.getDecorView().setSystemUiVisibility(visibility);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);

                fixTransparentStatusBar(view);
                // 最后fix一下状态栏背景白色与系统的文字图标白色的问题
                fixTransparentStatusBarWhiteTextColor(view, viewStatusbarBackground);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                WindowManager.LayoutParams localLayoutParams = window.getAttributes();
//                localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                fixTransparentStatusBar(view);
                // 最后fix一下状态栏背景白色与系统的文字图标白色的问题
                fixTransparentStatusBarWhiteTextColor(view, viewStatusbarBackground);
            } else {
                setStatusbarBackgroundGone();
            }
        } else {
            setStatusbarBackgroundGone();
        }
    }


    protected void setStatusbarBackgroundGone() {
        if (viewStatusbarBackground != null && viewStatusbarBackground.getVisibility() != View.GONE) {
            viewStatusbarBackground.setVisibility(View.GONE);
        }
        View v = findViewById(R.id.status_bar_background);
        // setContentViewNoTitlebar()的话，viewStatusbarBackground为null
        if (v != null && v.getVisibility() != View.GONE) {
            v.setVisibility(View.GONE);
        }
    }

    protected boolean isFixTransparentStatusBar() {
        return true;
    }

    protected void fixTransparentStatusBar(View view) {
        // 当出现自定义TransparentStatusbarView时，重载处理
    }

    public boolean fixTransparentStatusBarWhiteTextColor(View rootView, View viewTSBarBg) {
        // 是否需要更新状态栏背景，避免看不见状态栏白色的文字
        if (DeviceUtil.isMeizu()) {
            StatusbarColorUtils.fix(DeviceUtil.MEIZU, this ,true);
            return true;
        }else if (DeviceUtil.isMIUI()) {
            StatusbarColorUtils.fix(DeviceUtil.XIAOMI, this ,true);
            return true;
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // M及以上会使用亮色模式,不需要修改
            return false;
        } else if (viewTSBarBg != null && viewTSBarBg.getVisibility() == View.VISIBLE) {
            // 其它的机子在android 4.4到5.2之间的，都没办法改状态栏图标及文字的颜色，所以要改背景
            viewTSBarBg.setBackgroundResource(R.drawable.status_bar_background);
            return true;
        } else {
            return false;
        }
    }

}
