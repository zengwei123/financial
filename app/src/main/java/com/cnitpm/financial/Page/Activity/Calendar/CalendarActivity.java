package com.cnitpm.financial.Page.Activity.Calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;
import com.necer.calendar.Miui9Calendar;

/**
 * 日历  查看往日记录
 */
public class CalendarActivity extends MvpActivity<CalendarPresenter> implements CalendarView {
    @ViewBind(R.id.Include_Back)
    private ImageView Include_Back;   //返回
    @ViewBind(R.id.Include_Title)
    private TextView textView;     //标题
    @ViewBind(R.id.Calendar_TextView_YearMonth)
    private TextView Calendar_TextView_YearMonth;  //显示天的日年月日
    @ViewBind(R.id.Calendar_Miui9Calendar)
    private Miui9Calendar Calendar_Miui9Calendar;  //日历
    @ViewBind(R.id.Include_image)
    private ImageView Include_image;
    @ViewBind(R.id.Calendar_RecyclerView)
    private RecyclerView Calendar_RecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mvpPresenter.attachView(this);
        super.injectViews();
        mvpPresenter.init();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Activity getThisActivity() {
        return this;
    }

    @Override
    protected CalendarPresenter createPresenter() {
        return new CalendarPresenter();
    }

    @Override
    public ImageView getInclude_Back() {
        return Include_Back;
    }

    @Override
    public TextView getInclude_Title() {
        return textView;
    }

    @Override
    public TextView getCalendar_TextView_YearMonth() {
        return Calendar_TextView_YearMonth;
    }

    @Override
    public Miui9Calendar getCalendar_Miui9Calendar() {
        return Calendar_Miui9Calendar;
    }

    @Override
    public ImageView getInclude_image() {
        return Include_image;
    }

    @Override
    public RecyclerView getCalendar_RecyclerView() {
        return Calendar_RecyclerView;
    }
}
