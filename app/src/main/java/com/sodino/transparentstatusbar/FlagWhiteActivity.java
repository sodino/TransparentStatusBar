package com.sodino.transparentstatusbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/12.
 */
public class FlagWhiteActivity extends Activity implements View.OnClickListener{
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_flag_white);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = window.getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        findViewById(R.id.back).setOnClickListener(this);

        ((TextView)findViewById(R.id.content)).append("\n\nAPI Level v" + Build.VERSION.SDK_INT);
        findViewById(R.id.titleBar).setBackgroundResource(R.drawable.title_layout_white3);
        findViewById(R.id.titleBar).setPadding(0, (int)getResources().getDimension(R.dimen.translateStatusBarHeight), 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:{
                finish();
            }break;
        }
    }
}
