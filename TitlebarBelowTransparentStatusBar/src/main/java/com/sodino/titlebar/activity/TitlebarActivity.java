package com.sodino.titlebar.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sodino.titlebar.R;

/**
 * Created by Administrator on 2017/1/13.
 */

public class TitlebarActivity extends BaseActivity {
    protected View contentView;
    protected View viewTitlebar;
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
        contentView = view;

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LayoutInflater.from(this).inflate(R.layout.transparent_status_bar_bg_view, linearLayout, true);
        viewStatusbarBackground = linearLayout.findViewById(R.id.status_bar_background);

        LayoutInflater.from(this).inflate(R.layout.titlebar_original, linearLayout, true);
        viewTitlebar = linearLayout.findViewById(R.id.titlebar_layout);

        initTitlebarIDs(viewTitlebar);

        linearLayout.addView(contentView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        super.setContentView(linearLayout);
    }

    private void initTitlebarIDs(View viewTitlebar) {
        // do something....
    }

    public void setContentViewNoTitlebar(View view) {
        contentView = view;
        super.setContentView(view);
    }


}
