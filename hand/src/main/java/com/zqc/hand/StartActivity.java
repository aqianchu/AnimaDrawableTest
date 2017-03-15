package com.zqc.hand;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class StartActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4以上
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, Main2Activity.class));
                break;
        }
    }
}
