package com.cnitpm.financial.Page.Fragment.Chart;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnitpm.financial.Base.MvpFragment;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

public class ChartFragment  extends MvpFragment<ChartPrestenter> implements ChartView {
    @ViewBind(R.id.Chart_Average_TextView)
    private TextView Chart_Average_TextView;    //平均日消费
    @ViewBind(R.id.Chart_Month_TextView)
    private TextView Chart_Month_TextView;      //本月的日期
    @ViewBind(R.id.Chart_Situation_1)
    private TextView Chart_Situation_1;   //支出情况
    @ViewBind(R.id.Chart_Situation_2)
    private TextView Chart_Situation_2;   //收入情况
    @ViewBind(R.id.Chart_Situation_3)
    private TextView Chart_Situation_3;    //预算结余情况

    @ViewBind(R.id.Chart_Bar)
    private BarChart Chart_Bar;      //图表框架  柱状图
    @ViewBind(R.id.Chart_Pie)
    private PieChart Chart_Pie;    //图表框架  饼状图
    @ViewBind(R.id.Chart_Pie1)
    private PieChart Chart_Pie1;    //图表框架  饼状图
    @ViewBind(R.id.Chart_LR)
    private TextView Chart_LR;     //切换饼图的内容

    @ViewBind(R.id.Chart_NoteBooks_TextView)
    private TextView Chart_NoteBooks_TextView;  //用户切换账本

    @ViewBind(R.id.Chart_Comprehensive_LinearLayout)
    private LinearLayout Chart_Figure_LinearLayout;   //图标布局
    @ViewBind(R.id.Chart_Comprehensive_LinearLayout)
    private LinearLayout Chart_Comprehensive_LinearLayout;  //综合数据布局
    @ViewBind(R.id.Chart_NoteBooks_RecyclerView)
    private RecyclerView Chart_NoteBooks_RecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chart_layout,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter.attachView(this);
        injectViews(view);
        mvpPresenter.init();
    }

    @Override
    public Context getActivityContext() {
        return activity;
    }

    @Override
    public Activity getThisActivity() {
        return activity;
    }

    @Override
    protected ChartPrestenter createPresenter() {
        return new ChartPrestenter();
    }

    @Override
    public TextView getChart_Month_TextView() {
        return Chart_Month_TextView;
    }

    @Override
    public TextView getChart_Average_TextView() {
        return Chart_Average_TextView;
    }

    @Override
    public TextView getChart_Situation_1() {
        return Chart_Situation_1;
    }

    @Override
    public TextView getChart_Situation_2() {
        return Chart_Situation_2;
    }

    @Override
    public TextView getChart_Situation_3() {
        return Chart_Situation_3;
    }

    @Override
    public BarChart getChart_Bar() {
        return Chart_Bar;
    }

    @Override
    public PieChart getChart_Pie() {
        return Chart_Pie;
    }

    @Override
    public PieChart getChart_Pie1() {
        return Chart_Pie1;
    }

    @Override
    public TextView getChart_LR() {
        return Chart_LR;
    }

    @Override
    public TextView getChart_NoteBooks_TextView() {
        return Chart_NoteBooks_TextView;
    }

    @Override
    public LinearLayout getChart_Figure_LinearLayout() {
        return Chart_Figure_LinearLayout;
    }

    @Override
    public LinearLayout getChart_Comprehensive_LinearLayout() {
        return Chart_Comprehensive_LinearLayout;
    }

    @Override
    public RecyclerView getChart_NoteBooks_RecyclerView() {
        return Chart_NoteBooks_RecyclerView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mvpPresenter.msg(mvpPresenter.noteBookId);
        mvpPresenter.setNoteBook();
    }
}
