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
public class FitsSystemWindowWhiteActivity extends Activity implements View.OnClickListener {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fitssystemwindow_white);
        boolean needChanged = true;
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 亮色模式,避免系统状态栏的图标不可见
                visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

                needChanged = false;
            }
            window.getDecorView().setSystemUiVisibility(visibility);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = window.getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (needChanged) {
            findViewById(R.id.titleBar).setBackgroundResource(R.drawable.title_layout_white3);
            findViewById(R.id.titleBar).setPadding(0, (int)getResources().getDimension(R.dimen.translateStatusBarHeight), 0, 0);
        }

        findViewById(R.id.back).setOnClickListener(this);

        ((TextView)findViewById(R.id.content)).append("\n\nAPI Level v" + Build.VERSION.SDK_INT);
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
