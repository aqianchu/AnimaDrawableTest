package com.scu.ad;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView iv;
    private boolean flag;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button)findViewById(R.id.button1);bt.setOnClickListener(this);
        iv = (ImageView)findViewById(R.id.imageview);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                AnimationDrawable ad = (AnimationDrawable) iv.getBackground();
                if (flag){
                    flag = false;
                    ad.stop();
                    bt.setText("开始");
                }else {
                    flag = true;
                    ad.start();
                    bt.setText("暂停");
                }
                break;
        }
    }
}
