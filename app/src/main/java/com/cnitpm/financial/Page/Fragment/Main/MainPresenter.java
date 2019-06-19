package com.cnitpm.financial.Page.Fragment.Main;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.Page.Activity.Calendar.CalendarActivity;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.SqlOperation;
import com.cnitpm.financial.Util.UtilRecyclerAdapter;
import com.cnitpm.financial.Util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class MainPresenter extends BasePresenter<MainView> implements View.OnClickListener {
    private double Money_L,Money_R;
    private int budget=3000;//预算
    @Override
    public void init() {
        mvpView.getMain_Recycler_TimeLine().setLayoutManager(new LinearLayoutManager(mvpView.getActivityContext()));
        Refresh();
        //收入与支出总和按钮
        mvpView.getMain_TextView_Money_L().setText("本月收入\n"+Money_L);
        mvpView.getMain_TextView_Money_R().setText("本月支出\n"+Money_R);
        //title 日期按钮
        mvpView.getMain_TextView_Date().setText(Utils.getFormat("MM/dd",new Date().getTime()));
        //搜索按钮
        mvpView.getMain_TextView_Search().setTypeface(Utils.getTypeFace(mvpView.getActivityContext()));
        mvpView.getMain_TextView_Search().setText("\uecb3");
        //日历按钮
        mvpView.getMain_TextView_Calendar().setTypeface(Utils.getTypeFace(mvpView.getActivityContext()));
        mvpView.getMain_TextView_Calendar().setText("\uea7a");
        //日常账本
        mvpView.getMain_TextView_NoteBook().setTypeface(Utils.getTypeFace(mvpView.getActivityContext()));
        mvpView.getMain_TextView_NoteBook().setText("\uea7b日常账本");


        /**点击事件**/
        mvpView.getMain_TextView_Calendar().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Main_TextView_Calendar:
                ((BaseActivity)mvpView.getThisActivity()).JumpActivity(mvpView.getActivityContext(), CalendarActivity.class);
                break;
        }
    }


    public void Refresh(){
        List<AllModel> allModels=new ArrayList<>();
        List<TimeLine> timeLines=new SqlOperation().SelectWhere(TimeLine.class,"Time=?",Utils.getFormat("yyyy-MM-dd",new Date().getTime()));

        for(TimeLine timeLine:timeLines){
            allModels.add(new AllModel(timeLine,1));
        }

        for (int i=0;i<allModels.size();i++){
            TimeLine timeLine=(TimeLine)allModels.get(i).getData();
            if(timeLine.getDirection()){
                Money_L+=timeLine.getPrice();
            }else {
                Money_R+=timeLine.getPrice();
                budget-=Money_R;
            }
        }
        //收入与支出总和按钮
        mvpView.getMain_TextView_Money_L().setText("本月收入\n"+Money_L);
        mvpView.getMain_TextView_Money_R().setText("本月支出\n"+Money_R);
        mvpView.getMain_TextView_Budget().setText("预 算\n"+budget);
        mvpView.getMain_Recycler_TimeLine().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),TimeLine.class,allModels));
    }
}
