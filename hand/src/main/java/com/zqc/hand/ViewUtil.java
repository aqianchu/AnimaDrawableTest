package com.zqc.hand;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class ViewUtil {
	
    /** The minimum size of the bottom margin below the app to detect a keyboard. */
    private static final float KEYBOARD_DETECT_BOTTOM_THRESHOLD_DP = 100;


    public static float dp2px(Context context, float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static void triggerInputMethod(View view, boolean isShow){
        if (isShow) {
            InputMethodManager imm= (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }else {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean isKeyboardShowing(Context context, View view) {

        View rootView = view.getRootView();
        if (rootView == null) return false;
        Rect appRect = new Rect();
        rootView.getWindowVisibleDisplayFrame(appRect);

        final float density = context.getResources().getDisplayMetrics().density;
        final float bottomMarginDp = Math.abs(rootView.getHeight() - appRect.height()) / density;
        return bottomMarginDp > KEYBOARD_DETECT_BOTTOM_THRESHOLD_DP;
    }


}
