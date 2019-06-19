package com.cnitpm.financial.Page.Activity.Calendar;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.CalendarRecord;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.UtilRecyclerAdapter;
import com.cnitpm.financial.Util.Utils;
import com.necer.entity.NDate;
import com.necer.listener.OnCalendarChangedListener;
import com.necer.painter.InnerPainter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CalendarPresenter extends BasePresenter<CalendarView> {
    @Override
    public void init() {
        mvpView.getInclude_Back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpView.getThisActivity().finish();
            }
        });
        mvpView.getInclude_Title().setText("往日账单");
        //当前的月份
        mvpView.getCalendar_TextView_YearMonth().setText(Utils.getFormat("YYYY年MM月",new Date().getTime()));
        //隐藏的按钮
        mvpView.getInclude_image().setVisibility(View.VISIBLE);

        /**显示超额支出日期**/
        List<String> pointList = Arrays.asList("2019-05-01", "2019-05-19", "2019-05-20", "2019-05-23", "2019-05-01");
        InnerPainter innerPainter = (InnerPainter) mvpView.getCalendar_Miui9Calendar().getCalendarPainter();
        innerPainter.setPointList(pointList);

        mvpView.getCalendar_Miui9Calendar().setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarDateChanged(NDate date, boolean isClick) {
                //获取日历当前的年月
                mvpView.getCalendar_TextView_YearMonth().setText(date.lunar.lunarYear+"年"+(date.lunar.lunarMonth+1)+"月");
                //日历回调 NDate包含公历、农历、节气、节假日、闰年等信息
                if(isClick){
                    mvpView.getCalendar_TextView_YearMonth().setText(date.localDate.toString());
                    mvpView.getCalendar_RecyclerView().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),CalendarRecord.class,null));
                    /**添加空布局**/
                    View view=  LayoutInflater.from(mvpView.getActivityContext()).inflate(R.layout.z_recycler_nodata_item, null);
                    ((UtilRecyclerAdapter)mvpView.getCalendar_RecyclerView().getAdapter()).setEmptyView(view);
                }
            }
            @Override
            public void onCalendarStateChanged(boolean isMonthSate) {
                //日历状态回调， 月->周 isMonthSate返回false ，反之返回true
            }
        });

        /**每日查询**/
        mvpView.getCalendar_RecyclerView().setLayoutManager(new LinearLayoutManager(mvpView.getActivityContext()));
        mvpView.getCalendar_RecyclerView().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),CalendarRecord.class,DayRecord("")));
        /**添加空布局**/
        View view=  LayoutInflater.from(mvpView.getActivityContext()).inflate(R.layout.z_recycler_nodata_item, null);
        ((UtilRecyclerAdapter)mvpView.getCalendar_RecyclerView().getAdapter()).setEmptyView(view);
    }

    private List<AllModel> DayRecord(String string){

        List<AllModel> allModels=new ArrayList<>();
        for(int i=0;i<20;i++){
            CalendarRecord calendarRecord;
            if(i%2==1){
                calendarRecord=new CalendarRecord(true,1,"","1",i*2,0);
            }else {
                calendarRecord=new CalendarRecord(false,1,"","1",i*2,0);
            }
            if(i%3==1){
                calendarRecord.setMessage(null);
            }else {
                calendarRecord.setMessage("哈哈哈哈哈哈把啦啦啦小魔仙全身变五卡拉卡黑魔变身");
            }

            allModels.add(new AllModel(calendarRecord,1));
            if(i!=19){
                allModels.add(new AllModel(calendarRecord,2));
            }
        }
        return allModels;
    }
}
