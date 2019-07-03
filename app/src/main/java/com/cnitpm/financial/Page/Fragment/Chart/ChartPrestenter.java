package com.cnitpm.financial.Page.Fragment.Chart;

import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.NoteBook;
import com.cnitpm.financial.Util.SqlOperation;
import com.cnitpm.financial.Util.Utils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartPrestenter extends BasePresenter<ChartView> {
    private int noteBookId=1;    //账本id
    private double Budget=0;    //本月预算
    private double spending=0;   //本月花销
    private double income=0;    //本月收入
    private String month = Utils.getFormat("M",new Date().getTime());   //月份
    private String day = Utils.getFormat("d",new Date().getTime());     //日
    @Override
    public void init() {

        mvpView.getChart_Month_TextView().setText(month+"月1日-"+month+"月"+day+"日");
        msg(noteBookId);
        setBar(noteBookId);
    }
    private void msg(int ii){
        NoteBook noteBook = (NoteBook) new SqlOperation().SelectId(NoteBook.class,ii);
        Budget = noteBook.getBudget();
        spending=new ChartSql().getSpending(Utils.getFormat("yyyy-MM",new Date().getTime()),ii);
        income=new ChartSql().getIncome(Utils.getFormat("yyyy-MM",new Date().getTime()),ii);
        if(spending!=0){
            mvpView.getChart_Average_TextView().setText("当前日均消费"+formatDoubel(spending,Integer.parseInt(day))+"元");
        }
        /**设置支出 收入 结余**/
        mvpView.getChart_Situation_1().setText(setTextSpan("支出\n"+spending,2,"#5ED5D1",0.8f));
        mvpView.getChart_Situation_2().setText(setTextSpan("收入\n"+income,2,"#5ED5D1",0.8f));
        mvpView.getChart_Situation_3().setText(setTextSpan("预算结余\n"+(Budget-spending),4,"#5ED5D1",0.8f));
    }

    /**设置柱状图**/
    private void setBar(int ii){
        List<IBarDataSet> dataSets = new ArrayList<>();
        // 收入
        BarDataSet barDataSet = new BarDataSet(new ChartSql().getAllIncome(Utils.getFormat("yyyy-MM-",new Date().getTime()),ii,Integer.parseInt(month)), "收入");
        barDataSet.setColor(Color.parseColor("#5ED5D1"));
        // 支出
        BarDataSet barDataSet1 = new BarDataSet(new ChartSql().getAllSpending(Utils.getFormat("yyyy-MM-",new Date().getTime()),ii,Integer.parseInt(month)), "支出");
        barDataSet1.setColor(Color.parseColor("#F1AAA6"));
        dataSets.add(barDataSet);
        dataSets.add(barDataSet1);
        BarData mBarData = new BarData(dataSets);

        int barAmount = dataSets.size(); //需要显示柱状图的类别 数量
        //设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
        float groupSpace = 0.3f; //柱状图组之间的间距
        float barWidth = (1f - groupSpace) / barAmount;
        float barSpace = 0f;
        //设置柱状图宽度
        mBarData.setBarWidth(barWidth);
        //(起始点、柱状图组间距、柱状图之间间距)
        mBarData.groupBars(-0.5f, groupSpace, barSpace);

        /**设置内容**/
        mvpView.getChart_Bar().setData(mBarData);
        Description description = new Description();
        // 可以自定义 位置
        // description.setPosition(200, 200);
        description.setText("过去30天收入支出");
        mvpView.getChart_Bar().setDescription(description);
        //设置是否可以拖拽  
        mvpView.getChart_Bar().setDragEnabled(true);
        //是否缩放X轴  
        mvpView.getChart_Bar().setScaleXEnabled(true);
        //是否缩放Y轴  
        mvpView.getChart_Bar().setScaleYEnabled(false);
        //设置是否可以通过双击屏幕放大图表。默认是true  
        mvpView.getChart_Bar().setDoubleTapToZoomEnabled(false);
        mvpView.getChart_Bar().setVisibleXRange(0,10);

        XAxis xAxis = mvpView.getChart_Bar().getXAxis();
        // 设置 x 轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 取消 垂直 网格线
        xAxis.setDrawGridLines(false);
        // 设置 x 轴 坐标旋转角度
        xAxis.setLabelRotationAngle(45f);
        // 设置 x 轴 坐标字体大小
        xAxis.setTextSize(10f);
        // 设置 x 坐标轴 颜色
        xAxis.setAxisLineColor(Color.RED);
        // 设置 x 坐标轴 宽度
        xAxis.setAxisLineWidth(1f);
        // 设置 x轴 的刻度数量
        xAxis.setLabelCount(10);
        //设置x轴内容
        xAxis.setValueFormatter(new IndexAxisValueFormatter(Utils.getDays()));

        // 获取 右边 y 轴
        YAxis mRAxis = mvpView.getChart_Bar().getAxisRight();
        // 隐藏 右边 Y 轴
        mRAxis.setEnabled(false);
        // 获取 左边 Y轴
        YAxis mLAxis = mvpView.getChart_Bar().getAxisLeft();
        // 取消 左边 Y轴 坐标线
        mLAxis.setDrawAxisLine(false);
        // 取消 横向 网格线
        mLAxis.setDrawGridLines(false);
        // 设置 Y轴 的刻度数量
        mLAxis.setLabelCount(5);

    }



    /**格式化小数 保留两位小数字  防止出现很多很多小数点的情况**/
    private double formatDoubel(double d1,double d2){
        return new BigDecimal(d1/ d2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**设置一下字体的杨思**/
    private SpannableString setTextSpan(String s,int i,String color,float size){
        SpannableString spannableString = new SpannableString(s);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(size);
        spannableString.setSpan(colorSpan, i, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan01, i, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}