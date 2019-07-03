package com.cnitpm.financial.Page.Fragment.Chart;

import android.database.Cursor;
import android.util.Log;

import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.Util.Utils;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartSql {
    /**
     * 获取当月已经消费
     * @return
     */
    public static double getSpending(String data,int noteBookId){

        Cursor cursor= LitePal.findBySQL("select sum(price) as A from timeline where notebook=? AND direction=1 AND time like ? ",noteBookId+"",data+"%");
        while (cursor.moveToNext()){
            return cursor.getDouble(cursor.getColumnIndex("A"));
        }
        return 0;
    }

    /**
     * 获取当月已经收入
     * @return
     */
    public static double getIncome(String data,int noteBookId){
        Cursor cursor= LitePal.findBySQL("select sum(price) as A from timeline where notebook=? AND direction=0  AND time like ?",noteBookId+"",data+"%");
        while (cursor.moveToNext()){
            return cursor.getDouble(cursor.getColumnIndex("A"));
        }
        return 0;
    }
    /**
     * 获取当月全部支出
     * @return
     */
    public static List<BarEntry> getAllSpending(String data,int noteBookId,int mm){
        List<BarEntry> barEntries=new ArrayList<>();
        List<String> strings=Utils.getDays();
        for(int i=0;i<30;i++){
            Cursor cursor= LitePal.findBySQL("select Price as A from timeline where time=? and notebook=? and  direction=1  ",
                    Utils.getFormat("yyyy-",new Date().getTime())+strings.get(i),noteBookId+"");
            if (cursor.moveToNext()){
                barEntries.add(new BarEntry(i,cursor.getFloat(cursor.getColumnIndex("A"))));
            }else {
                barEntries.add(new BarEntry(i,0));
            }
        }
        return barEntries;
    }

    /**
     * 获取当月全部收入
     * @return
     */
    public static List<BarEntry> getAllIncome(String data,int noteBookId,int mm){
        List<BarEntry> barEntries=new ArrayList<>();
        List<String> strings=Utils.getDays();
        for(int i=0;i<30;i++){
            Cursor cursor= LitePal.findBySQL("select Price as A from timeline where time=? and notebook=? and  direction=0  ",
                    Utils.getFormat("yyyy-",new Date().getTime())+strings.get(i),noteBookId+"");
            if (cursor.moveToNext()){
                barEntries.add(new BarEntry(i,cursor.getFloat(cursor.getColumnIndex("A"))));
            }else {
                barEntries.add(new BarEntry(i,0));
            }
        }
        return barEntries;
    }


    /**
     * 获取当月全部收入类型
     * @return
     */
    public static List<PieEntry> getAllClassL(int noteBookId){
        List<PieEntry> barEntries=new ArrayList<>();
            Cursor cursor= LitePal.findBySQL("select Price as A,Icon_Class as B from timeline where notebook=? and  direction=0  group by Icon_Class",
                   noteBookId+"");
            while (cursor.moveToNext()){
                barEntries.add(new PieEntry(cursor.getFloat(cursor.getColumnIndex("A")),Utils.LeftClass[cursor.getInt(cursor.getColumnIndex("B"))]));
            }
        return barEntries;
    }

    /**
     * 获取当月全部收入类型
     * @return
     */
    public static List<PieEntry> getAllClassR(int noteBookId){
        List<PieEntry> barEntries=new ArrayList<>();
        Cursor cursor= LitePal.findBySQL("select Price as A,Icon_Class as B from timeline where notebook=? and  direction=1  group by Icon_Class",
                noteBookId+"");
        while (cursor.moveToNext()){
            barEntries.add(new PieEntry(cursor.getFloat(cursor.getColumnIndex("A")),Utils.RightClass[cursor.getInt(cursor.getColumnIndex("B"))]));
        }
        return barEntries;
    }
}
