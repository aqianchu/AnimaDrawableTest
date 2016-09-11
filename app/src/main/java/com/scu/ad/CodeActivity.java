package com.scu.ad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CodeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt;
    private AnimationDrawable animationDrawable;
    private ImageView iv;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        bt = (Button)findViewById(R.id.button2);
        bt.setOnClickListener(this);
        animationDrawable = new AnimationDrawable();
        loadImages();
        animationDrawable.setOneShot(false);
        iv = (ImageView)findViewById(R.id.imageview1);
        iv.setBackground(animationDrawable);
    }

    private void loadImages() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.loading_1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.loading_2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.loading_3);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(),R.drawable.loading_4);
        animationDrawable.addFrame(new BitmapDrawable(bitmap),200);
        animationDrawable.addFrame(new BitmapDrawable(bitmap2),200);
        animationDrawable.addFrame(new BitmapDrawable(bitmap3),200);
        animationDrawable.addFrame(new BitmapDrawable(bitmap4),200);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
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
