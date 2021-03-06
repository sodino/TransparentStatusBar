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
public class PaddingTopRedActivity extends Activity implements View.OnClickListener {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_paddingtop_red);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.holo_red_light));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
