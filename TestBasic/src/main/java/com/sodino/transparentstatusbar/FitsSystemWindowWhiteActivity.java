package com.sodino.transparentstatusbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/12.
 */
public class FitsSystemWindowWhiteActivity extends Activity implements View.OnClickListener {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fitssystemwindow_white);

        boolean needChanged = false;
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            needChanged = true;
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
            needChanged = true;
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (needChanged) {
            findViewById(R.id.titleBar).setBackgroundResource(R.drawable.title_layout_white3);
            findViewById(R.id.titleBar).setPadding(0, (int)getResources().getDimension(R.dimen.translateStatusBarHeight), 0, 0);
        }

        findViewById(R.id.back).setOnClickListener(this);

        TextView txt = ((TextView)findViewById(R.id.content));
        txt.setText("API Level v" + Build.VERSION.SDK_INT + "\n\n");
        txt.append("Device : " + Build.FINGERPRINT + "\n\n");
        txt.append(getString(R.string.content));
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
