package com.scu.ad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.xml_id).setOnClickListener(this);
        findViewById(R.id.code_id).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.code_id:
                startActivity(new Intent(this,CodeActivity.class));
                break;
            case R.id.xml_id:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}
