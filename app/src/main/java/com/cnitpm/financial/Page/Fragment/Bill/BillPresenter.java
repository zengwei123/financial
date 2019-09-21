package com.cnitpm.financial.Page.Fragment.Bill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.BillModel;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.Page.Activity.A;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.SqlOperation;
import com.cnitpm.financial.Util.UtilRecyclerAdapter;
import com.cnitpm.financial.Util.Utils;

import java.util.ArrayList;
import java.util.List;

public class BillPresenter extends BasePresenter<BillView> implements View.OnClickListener {
    private String InitialDay="-";
    private String EndDay="-";
    List<BillModel> billModels;
    @Override
    public void init() {
        click();
        setRecycler();
    }
    private void click(){
        mvpView.getBill_Add_Button().setOnClickListener(this);
        mvpView.getBill_Add_Layout().setOnClickListener(this);
        mvpView.getBill_End_Text().setOnClickListener(this);
        mvpView.getBill_Initial_Text().setOnClickListener(this);
        mvpView.getBill_Generate().setOnClickListener(this);
        mvpView.getadd_butssadas().setOnClickListener(this);
    }


    private void setRecycler(){
        billModels=new SqlOperation().SelectAll(BillModel.class);
        List<AllModel> allModels=new ArrayList<>();
        for (BillModel billModel:billModels){
            allModels.add(new AllModel<BillModel>(billModel,1));
        }
        UtilRecyclerAdapter utilRecyclerAdapter=new UtilRecyclerAdapter(mvpView.getActivityContext(),BillModel.class,allModels);
        mvpView.getBill_Recycler().setAdapter(utilRecyclerAdapter);
        mvpView.getBill_Recycler().setLayoutManager(new LinearLayoutManager(mvpView.getActivityContext()));
        utilRecyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putInt("key",position);
                ((BaseActivity)mvpView.getThisActivity()).JumpBundleActivity(mvpView.getActivityContext(), A.class,bundle);
            }
        });
        if (billModels.size()!=0){
            mvpView.getBill_Add_Button().setVisibility(View.GONE);
            mvpView.getadd_butssadas().setVisibility(View.VISIBLE);
        }else {
            mvpView.getBill_Add_Button().setVisibility(View.VISIBLE);
            mvpView.getadd_butssadas().setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Bill_Add_Button:
                mvpView.getBill_Add_Layout().setVisibility(View.VISIBLE);
                if (billModels.size()!=0){
                    mvpView.getBill_Add_Button().setVisibility(View.GONE);
                    mvpView.getadd_butssadas().setVisibility(View.VISIBLE);
                }else {
                    mvpView.getBill_Add_Button().setVisibility(View.GONE);
                    mvpView.getadd_butssadas().setVisibility(View.GONE);
                }
                mvpView.getBill_Initial_Text().setText("开始时间："+InitialDay);
                mvpView.getBill_End_Text().setText("结束时间："+EndDay);
                break;
            case R.id.Bill_Add_Layout:
                mvpView.getBill_Add_Layout().setVisibility(View.GONE);
                if (billModels.size()!=0){
                    mvpView.getBill_Add_Button().setVisibility(View.GONE);
                    mvpView.getadd_butssadas().setVisibility(View.VISIBLE);
                }else {
                    mvpView.getBill_Add_Button().setVisibility(View.VISIBLE);
                    mvpView.getadd_butssadas().setVisibility(View.GONE);
                }
                InitialDay="-";
                EndDay="-";
                break;
            case R.id.Bill_End_Text:
                if (InitialDay.equals("-")){
                    Toast.makeText(mvpView.getActivityContext(), "请先选择初始时间", Toast.LENGTH_SHORT).show();
                }else {
                    getAddTime(false);
                }
                break;
            case R.id.Bill_Initial_Text:
                getAddTime(true);
                break;
            case R.id.Bill_Generate:
                List<TimeLine> calendarRecords=new SqlOperation().SelectAll(TimeLine.class);
                if (calendarRecords.size()==0){
                    Toast.makeText(mvpView.getActivityContext(), "没有账单可设生成", Toast.LENGTH_SHORT).show();
                    return;
                }
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
                BillModel billModel=new BillSql(-1,A,B).SelectAll();
                billModel.save();
                mvpView.getBill_Add_Layout().setVisibility(View.GONE);
                init();
                break;
            case R.id.add_butssadas:
                mvpView.getBill_Add_Layout().setVisibility(View.VISIBLE);
                mvpView.getBill_Add_Button().setVisibility(View.GONE);
                mvpView.getBill_Initial_Text().setText("开始时间："+InitialDay);
                mvpView.getBill_End_Text().setText("结束时间："+EndDay);
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
                    String[] strings=InitialDay.split("-");
                    String[] strings1=EndDay.split("-");
                    if (Integer.parseInt(strings[0])>Integer.parseInt(strings1[0])||Integer.parseInt(strings[1])>Integer.parseInt(strings1[1])||Integer.parseInt(strings[2])>Integer.parseInt(strings1[2])){
                        Toast.makeText(mvpView.getActivityContext(), "结束时间不能大于或等于开始时间!", Toast.LENGTH_SHORT).show();
                        EndDay="-";
                    }
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
    private String text(BillModel billModel){
        String string="这个账单创建于:"+billModel.getCreateTime()+"\n"+
                "账单记录的开始时间是:"+billModel.getInitialDay()+"\n"+
                "而账单记录的结束时间是："+billModel.getEndDay()+"\n"+
                "账单记录跨越"+billModel.getSumDay()+"天，\n"+
                "这个账单是你所有的账本的这段时间的记录\n"+
                "其中你收入最多的账本是："+billModel.getMaxIncomeMoneyNoteBook()+"\n"+
                "而支出最多的账户则是："+billModel.getMaxSpendingMoneyNoteBook()+"\n"+
                "你所有的支出金额是："+billModel.getSpendingSumMoney()+"\n"+
                "所有的收入是："+billModel.getIncomeSumMoney()+"\n"+
                "在"+billModel.getMaxSpendingMoneyNoteBook()+"个账本中，"+billModel.getMaxSpendingMoneyTime()+"这一天出现了最大的一笔支出，一共支出了："+billModel.getMaxSpendingMoney()+"\n"+
                "而支出的类型是："+Utils.RightClass[billModel.getMaxSpendingMoneyClass()]+"\n"+
                "另外在"+billModel.getMaxIncomeMoneyNoteBook()+"个账本中，"+billModel.getMaxIncomeMoneyTime()+"这一天出现了最大的一笔收入，一共收入了："+billModel.getMaxIncomeMoney()+"\n"+
                "而收入的类型是："+Utils.LeftClass[billModel.getMaxIncomeMoneyClass()]+"\n"+
                "在这么多天中平均每天支出："+billModel.getAverageSpendingMoney()+",而支出最多的是："+Utils.RightClass[billModel.getSpendingFrequencyClass()]+"一共有"+billModel.getSpendingFrequency()+"次支出\n"+
                "每天的收入则是："+billModel.getAverageIncomeMoney()+",而支出最多的是："+Utils.LeftClass[billModel.getIncomeFrequencyClass()]+"一共有"+billModel.getIncomeFrequency()+"次收入\n";


        return string;
    }

}
