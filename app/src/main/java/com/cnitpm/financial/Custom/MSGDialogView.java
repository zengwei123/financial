package com.cnitpm.financial.Custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.ScreenSizeUtils;


/**
 * Created by zengwei on 2019/1/20.
 */

public class MSGDialogView {
    private  Dialog dialog;
    private  View view;
    private  TextView DialogView_View_Title,DialogView_View_Message,DialogView_View_Cancel,DialogView_View_Confirm;


    public  Dialog getDialogView(Activity activity){
        dialog = new Dialog(activity, R.style.NormalDialogStyle);
        view = View.inflate(activity, R.layout.z_dialogview_view, null);
        DialogView_View_Title=view.findViewById(R.id.DialogView_View_Title);
        DialogView_View_Message=view.findViewById(R.id.DialogView_View_Message);
        DialogView_View_Cancel=view.findViewById(R.id.DialogView_View_Cancel);
        DialogView_View_Confirm=view.findViewById(R.id.DialogView_View_Confirm);
        dialog.setContentView(view);
        //使得点击对话框外部不消失对话框
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (ScreenSizeUtils.getInstance(activity.getApplicationContext()).getScreenWidth()/4)*3;
        //这里设置对话框的位置center是居中还可以设置bottom等
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        return dialog;
    }
    public  TextView getDialogView_View_Title(){
        return DialogView_View_Title;
    }
    public  TextView getDialogView_View_Message(){
        return DialogView_View_Message;
    }
    public  TextView getDialogView_View_Cancel(){
        return DialogView_View_Cancel;
    }
    public  TextView getDialogView_View_Confirm(){
        return DialogView_View_Confirm;
    }
}
