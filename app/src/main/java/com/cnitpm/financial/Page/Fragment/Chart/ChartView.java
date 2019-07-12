package com.cnitpm.financial.Page.Fragment.Chart;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

public interface ChartView extends BaseView {
    TextView getChart_Month_TextView();
    TextView getChart_Average_TextView();
    TextView getChart_Situation_1();
    TextView getChart_Situation_2();
    TextView getChart_Situation_3();
    BarChart getChart_Bar();
    PieChart getChart_Pie();
    PieChart getChart_Pie1();
    TextView getChart_LR();
    TextView getChart_NoteBooks_TextView();

    LinearLayout getChart_Figure_LinearLayout();
    LinearLayout getChart_Comprehensive_LinearLayout();
    RecyclerView getChart_NoteBooks_RecyclerView();
}
