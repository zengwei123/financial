package com.cnitpm.financial.Page.Fragment.Bill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.Utils;

public class BillPresenter extends BasePresenter<BillView> implements View.OnClickListener {
    private String InitialDay=null;
    private String EndDay=null;
    @Override
    public void init() {
        click();
    }
    private void click(){
        mvpView.getBill_Add_Button().setOnClickListener(this);
        mvpView.getBill_Add_Layout().setOnClickListener(this);
        mvpView.getBill_End_Text().setOnClickListener(this);
        mvpView.getBill_Initial_Text().setOnClickListener(this);
        mvpView.getBill_Generate().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Bill_Add_Button:
                mvpView.getBill_Add_Layout().setVisibility(View.VISIBLE);
                mvpView.getBill_Add_Button().setVisibility(View.GONE);
                mvpView.getBill_Initial_Text().setText("开始时间："+InitialDay);
                mvpView.getBill_End_Text().setText("结束时间："+EndDay);
                break;
            case R.id.Bill_Add_Layout:
                mvpView.getBill_Add_Layout().setVisibility(View.GONE);
                mvpView.getBill_Add_Button().setVisibility(View.VISIBLE);
                InitialDay="-";
                EndDay="-";
                break;
            case R.id.Bill_End_Text:
                getAddTime(false);
                break;
            case R.id.Bill_Initial_Text:
                getAddTime(true);
                break;
            case R.id.Bill_Generate:
                String A=null,B=null;
               if (InitialDay==null||InitialDay.equals("-")){
                   A=null;
               }else {
                   A=InitialDay;
               }

                if (EndDay==null||EndDay.equals("-")){
                    B=null;
                }else {
                    B=EndDay;
                }
                new BillSql(-1,A,B).SelectAll();
                break;
        }
    }

    /**选择日期的方法**/
    private void getAddTime(final boolean b){
        AlertDialog.Builder builder = new AlertDialog.Builder(mvpView.getActivityContext());
        View dialogView = View.inflate(mvpView.getActivityContext(), R.layout.z_dialog_date_layout, null);
        final DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
        builder.setPositiveButton("选择", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append(datePicker.getYear()+"-");
                if(datePicker.getMonth()<9){
                    stringBuilder.append("0"+(datePicker.getMonth()+1)+"-");
                }else {
                    stringBuilder.append((datePicker.getMonth()+1)+"-");
                }
                if(datePicker.getDayOfMonth()<10){
                    stringBuilder.append("0"+datePicker.getDayOfMonth());
                }else {
                    stringBuilder.append(datePicker.getDayOfMonth());
                }
                if (b){
                    InitialDay=stringBuilder.toString();
                    mvpView.getBill_Initial_Text().setText("开始时间："+InitialDay);
                }else {
                    EndDay=stringBuilder.toString();
                    mvpView.getBill_End_Text().setText("结束时间："+EndDay);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.setTitle("设置日期");
        dialog.setView(dialogView);
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) Utils.getWindow(true,mvpView.getThisActivity())-100;
        params.height = (int) Utils.getWindow(false,mvpView.getThisActivity())-100;
        dialog.getWindow().setAttributes(params);
    }


}
