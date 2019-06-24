package com.cnitpm.financial.Page.Activity.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.Utils;
import com.necer.calendar.Miui9Calendar;

import java.util.Date;

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

    private Bundle bundle;  //用来获取当前的账本信息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        bundle=getIntent().getBundleExtra("KEY");
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

    @Override
    public Bundle getBundle() {
        return bundle;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 110:
                /**这里是当从日历跳转到 添加页面后添加完之后 返回  需要刷新数据 **/
                mvpPresenter.DayRecord(mvpPresenter.BTime);
                getCalendar_RecyclerView().getAdapter().notifyDataSetChanged();
                mvpPresenter.chaochu(Utils.getFormat("YYYY-MM",new Date().getTime()));
                break;
        }
    }
}
