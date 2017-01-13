package com.sodino.transparentstatusbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnWhitePaddingTop).setOnClickListener(this);
        findViewById(R.id.btnRedPaddingTop).setOnClickListener(this);
        findViewById(R.id.btnFitssystemwindowRed).setOnClickListener(this);
        findViewById(R.id.btnFitssystemwindowWhite).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnWhitePaddingTop:{
                Intent intent = new Intent(this, PaddingTopWhiteActivity.class);
                startActivity(intent);
            }break;
            case R.id.btnRedPaddingTop:{
                Intent intent = new Intent(this, PaddingTopRedActivity.class);
                startActivity(intent);
            }break;
            case R.id.btnFitssystemwindowWhite:{
                Intent intent = new Intent(this, FitsSystemWindowWhiteActivity.class);
                startActivity(intent);
            }break;
            case R.id.btnFitssystemwindowRed:{
                Intent intent = new Intent(this, FitsSystemWindowRedActivity.class);
                startActivity(intent);
            }break;
        }
    }
}
