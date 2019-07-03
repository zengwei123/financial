package com.cnitpm.financial.Page.Fragment.Chart;

import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.github.mikephil.charting.charts.BarChart;

public interface ChartView extends BaseView {
    TextView getChart_Month_TextView();
    TextView getChart_Average_TextView();
    TextView getChart_Situation_1();
    TextView getChart_Situation_2();
    TextView getChart_Situation_3();
    BarChart getChart_Bar();
}
