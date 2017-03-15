package com.zqc.hand;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;


public class Main2Activity extends Activity implements View.OnClickListener {

    private Main2Activity context;
    private View root;
    private PopupWindow popupWindow;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = findViewById(R.id.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4以上
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        findViewById(R.id.test3).setOnClickListener(this);
        findViewById(R.id.test4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test3:
                initPopuWindow();
                break;
            case R.id.test4:
                Toast.makeText(this, "ceshi", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    int handTime = 800 * 2 + 800;
    int handStart = 1800;

    private void initPopuWindow() {
        final View popupWindowView = getLayoutInflater().inflate(R.layout.hand_title, null);
        //内容，高度，宽度
        final ImageView handView = (ImageView) popupWindowView.findViewById(R.id.imageView2);
        final Animation scale2zero = AnimationUtils.loadAnimation(context, R.anim.alpha_animation_hand);
        final Animation translate = AnimationUtils.loadAnimation(this, R.anim.hand_translate1);
        scale2zero.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handView.clearAnimation();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handView.startAnimation(translate);
                    }
                }, 0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handView.clearAnimation();
                handView.startAnimation(scale2zero);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //动画效果

        popupWindow.setAnimationStyle(R.style.AnimationRightFade1);

        popupWindow.setBackgroundDrawable(null);
        //宽度
        //popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
        //高度
        //popupWindow.setHeight(LayoutParams.FILL_PARENT);
        //显示位置

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handView.startAnimation(translate);
//                animation.start();
            }
        }, handStart);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                hand_root.startAnimation(alpha);
                popupWindow.dismiss();
            }
        }, handStart + handTime + 800);//800表示停留800消失
        //设置背景半透明
        backgroundAlpha(1f);
        //关闭事件
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);
//        popupWindow.setTouchable(true);
        popupWindowView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /*if( popupWindow!=null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                    popupWindow=null;
                }*/
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });
//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // 如果点击了popupwindow的外部，popupwindow也会消失
//                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                    //popupMenu.dismiss();
////                    StationEventCaptain.getInstance().fireEventDataChange(
////                            new StationEventData(IStationEventName.HEDGE_KEYBORAD_DIMISS, true));
//                    return true;
//                }
//                return false;
//            }
//        });

        popupWindow.showAtLocation(root, Gravity.RIGHT | Gravity.TOP, 0, (int) ViewUtil.dp2px(this, 271));
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
}
