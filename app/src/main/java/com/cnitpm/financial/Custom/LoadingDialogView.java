package com.cnitpm.financial.Custom;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.cnitpm.financial.R;

/**
 * Created by zengwei on 2019/1/25.
 */

public class LoadingDialogView {
    private static Dialog dialog;
    private static View view;
    public static LottieAnimationView lottieAnimationView1;
    public static Dialog getDialogView(Activity activity){
        dialog = new Dialog(activity, R.style.NormalDialogStyle1);
        view = View.inflate(activity, R.layout.dialogview_view1, null);
        lottieAnimationView1=view.findViewById(R.id.animation_view1);
        dialog.setContentView(view);
        //使得点击对话框外部不消失对话框
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //这里设置对话框的位置center是居中还可以设置bottom等
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        lottieAnimationView1.playAnimation();
        return dialog;
    }
}
