package com.sodino.titlebar.activity;

import android.os.Bundle;
import android.view.View;

import com.sodino.titlebar.R;

public class MainActivity extends TitlebarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean fixTransparentStatusBarWhiteTextColor(View rootView, View viewTSBarBg) {
        viewTSBarBg.setVisibility(View.GONE);
        return true;
    }
}
