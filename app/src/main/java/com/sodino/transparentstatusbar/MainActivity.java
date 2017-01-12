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

        findViewById(R.id.btnWhite).setOnClickListener(this);
        findViewById(R.id.btnRed).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnWhite:{
                Intent intent = new Intent(this, FlagWhiteActivity.class);
                startActivity(intent);
            }break;
            case R.id.btnRed:{
                Intent intent = new Intent(this, FlagRedActivity.class);
                startActivity(intent);
            }break;
        }
    }
}
