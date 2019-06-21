package com.cnitpm.financial.Page.Activity.Calendar;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.CalendarRecord;
import com.cnitpm.financial.Model.NoteBook;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.SqlOperation;
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
    private NoteBook noteBook;
    private double daybalance=0;

    private List<AllModel> allModels=new ArrayList<>();
    @Override
    public void init() {
        noteBook= (NoteBook) mvpView.getBundle().getSerializable("key");
        getDaybalance(Integer.parseInt(Utils.getFormat("M",new Date().getTime())));
        chaochu(Utils.getFormat("YYYY-MM",new Date().getTime()));
        mvpView.getInclude_Back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpView.getThisActivity().finish();
            }
        });
        mvpView.getInclude_Title().setText("往日账单("+noteBook.getNoteBookName()+")");
        //当前的月份
        mvpView.getCalendar_TextView_YearMonth().setText(Utils.getFormat("YYYY年MM月",new Date().getTime()));
        //隐藏的按钮
        mvpView.getInclude_image().setVisibility(View.VISIBLE);


        /**显示超额支出日期**/
        DayRecord(Utils.getFormat("YYYY-MM-dd",new Date().getTime()));

        mvpView.getCalendar_Miui9Calendar().setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarDateChanged(NDate date, boolean isClick) {
                //获取日历当前的年月
                mvpView.getCalendar_TextView_YearMonth().setText(date.lunar.lunarYear+"年"+(date.lunar.lunarMonth+1)+"月");
                //日历回调 NDate包含公历、农历、节气、节假日、闰年等信息
                if(isClick){
                    mvpView.getCalendar_TextView_YearMonth().setText(date.localDate.toString());
                    DayRecord(date.localDate.toString());
                    mvpView.getCalendar_RecyclerView().getAdapter().notifyDataSetChanged();
//                    mvpView.getCalendar_RecyclerView().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),CalendarRecord.class,null));
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
        mvpView.getCalendar_RecyclerView().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),CalendarRecord.class,allModels));
        /**添加空布局**/
        View view=  LayoutInflater.from(mvpView.getActivityContext()).inflate(R.layout.z_recycler_nodata_item, null);
        ((UtilRecyclerAdapter)mvpView.getCalendar_RecyclerView().getAdapter()).setEmptyView(view);
    }

    private void DayRecord(String time){
        allModels.clear();
        List<String> pointList =new ArrayList<>();
        List<TimeLine> timeLines=new SqlOperation().SelectWhere(TimeLine.class,"Time=? and NoteBook=?",time,noteBook.getId()+"");
        for(int i=0;i<timeLines.size();i++){
            TimeLine timeLine=timeLines.get(i);
            CalendarRecord calendarRecord=new CalendarRecord(timeLine.getDirection()
                    ,timeLine.getIcon_Class()
                    ,timeLine.getMessage()
                    ,timeLine.getImageUrl()
                    ,timeLine.getTime()
                    ,timeLine.getPrice()
                    ,timeLine.getNoteBook());

            allModels.add(new AllModel(calendarRecord,1));
            if(i!=timeLines.size()-1){
                allModels.add(new AllModel(calendarRecord,2));
            }
        }
    }

    private void chaochu(String time){
        double sumprice=0;
        //
        List<String> timeLines=new SqlOperation().SelectSql("select sum(Price) as A from timeline where time like ? and notebook=? group by time;",time+"%",noteBook.getId()+"");
        for(String s:timeLines){
            Log.d("zengwie123",s.toString());
        }
//        if(sumPrice>daybalance){
//            pointList.add(time);
//        }
//        InnerPainter innerPainter = (InnerPainter) mvpView.getCalendar_Miui9Calendar().getCalendarPainter();
//        innerPainter.setPointList(pointList);
    }

    private void getDaybalance(int m){
        switch (m){
            case 1: daybalance=31;break;
            case 2:daybalance=28;break;
            case 3:daybalance=31;break;
            case 4:daybalance=30;break;
            case 5:daybalance=31;break;
            case 6:daybalance=30;break;
            case 7:daybalance=31;break;
            case 8:daybalance=31;break;
            case 9:daybalance=30;break;
            case 10:daybalance=31;break;
            case 11:daybalance=30;break;
            case 12:daybalance=31;break;
        }
    }
}
