package com.sodino.transparentstatusbar;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/12.
 */
public class FitsSystemWindowRedActivity extends Activity implements View.OnClickListener {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_fitssystemwindow_red);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.holo_red_light));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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

    @Override
    public void onWindowFocusChanged(boolean focused){
        super.onWindowFocusChanged(focused);
        if (focused) {
            View v = findViewById(R.id.titleBar);
            Log.d("Test", "padding top=" + v.getPaddingTop() + " bottom=" + v.getPaddingBottom());
        }
    }
}
