package com.cnitpm.financial.Page.Fragment.Bill;

import android.content.Intent;
import android.util.Log;

import com.cnitpm.financial.Model.BillModel;
import com.cnitpm.financial.Model.CalendarRecord;
import com.cnitpm.financial.Model.NoteBook;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.Util.SqlOperation;
import com.cnitpm.financial.Util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**账单内容生成
 *
 * **/
public class BillSql {
    private int NoteBookId=-1;   //账本id
    private String InitialDay=null;   //开始时间
    private String EndDay=null;  //结束时间
    private int DateSum=0;  //总天数
    private int sumsp=0;  //支出次数
    private int sumIn=0;  //收入次数
    private BillModel billModel;

    public BillSql(int NoteBookId,String InitialDay,String EndDay){
        this.NoteBookId=NoteBookId;
        this.InitialDay=InitialDay;
        this.EndDay=EndDay;
        billModel=new BillModel();
    }

    /**
     * 创建时间**/
    private String CreateTime(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public BillModel SelectAll(){
        if (NoteBookId!=-1){
            s1();
            List<TimeLine> calendarRecords=new SqlOperation().SelectWhere(TimeLine.class,"notebook=?",NoteBookId+"");
            for (TimeLine calendarRecord:calendarRecords){
                a(calendarRecord);
            }
            billModel.setAverageSpendingMoney(billModel.getSpendingSumMoney()/DateSum);  //平均每天的支出
            billModel.setAverageIncomeMoney(billModel.getIncomeSumMoney()/DateSum);  //平均每天的收入
            billModel.setSpendingFrequency(sumsp);   //支出天数
            billModel.setIncomeFrequency(sumIn);   //收入天数
            billModel.setNoteBookId(NoteBookId);  //账本id
            return billModel;
        }else {
            s();
            List<TimeLine> calendarRecords=new SqlOperation().SelectAll(TimeLine.class);
            for (TimeLine calendarRecord:calendarRecords){
                a(calendarRecord);
            }
            billModel.setAverageSpendingMoney(billModel.getSpendingSumMoney()/DateSum);  //平均每天的支出
            billModel.setAverageIncomeMoney(billModel.getIncomeSumMoney()/DateSum);  //平均每天的收入
            billModel.setSpendingFrequency(sumsp);   //支出天数
            billModel.setIncomeFrequency(sumIn);   //收入天数
            billModel.setNoteBookId(NoteBookId);  //账本id
            b();
            c();
            return billModel;
        }
    }
    private void s(){
        /**获得日期和天数综合**/
        List<String> mimtime=new SqlOperation().SelectSql("select time as A,notebook as B from timeline where time= (select MIN(time) from timeline);");
        List<String> maxtime=new SqlOperation().SelectSql("select time as A,notebook as B from timeline where time= (select MAX(time) from timeline);");
        if (InitialDay==null){
            InitialDay=mimtime.get(0).split("#")[0];
        }
        if (EndDay==null){
            EndDay=maxtime.get(0).split("#")[0];
        }
        billModel.setCreateTime(CreateTime());
        billModel.setInitialDay(InitialDay);
        billModel.setEndDay(EndDay);
        billModel.setNoteBookName("全部账本");
        /**获得天数**/
        try {
            DateSum=Utils.longOfTwoDate(InitialDay,EndDay)+1;
        } catch (ParseException e) {
            e.printStackTrace();
            DateSum=0;
        }
        billModel.setSumDay(DateSum);   //天数量
    }
    private void s1(){
        /**获得日期和天数综合**/
        List<String> mimtime=new SqlOperation().SelectSql("select time as A,notebook as B from timeline where time= (select MIN(time) from timeline) and notebook=?;",NoteBookId+"");
        List<String> maxtime=new SqlOperation().SelectSql("select time as A,notebook as B from timeline where time= (select MAX(time) from timeline) and notebook=?;",NoteBookId+"");
        if (InitialDay==null){
            InitialDay=mimtime.get(0).split("#")[0];
        }
        if (EndDay==null){
            EndDay=maxtime.get(0).split("#")[0];
        }
        billModel.setCreateTime(CreateTime());  //创建时间
        billModel.setInitialDay(InitialDay);   //起始时间
        billModel.setEndDay(EndDay);  //结束时间
        NoteBook book= (NoteBook) new SqlOperation().SelectId(NoteBook.class,NoteBookId);
        billModel.setNoteBookName(book.getNoteBookName());
        /**获得天数**/
        try {
            DateSum=Utils.longOfTwoDate(InitialDay,EndDay)+1;
        } catch (ParseException e) {
            e.printStackTrace();
            DateSum=0;
        }
        billModel.setSumDay(DateSum);   //天数量
    }
    private void a(TimeLine calendarRecord){
        if (calendarRecord.getDirection()==1){
            sumsp++;
            billModel.setSpendingSumMoney(billModel.getSpendingSumMoney()+calendarRecord.getPrice());  //全部支出金额
            if (billModel.getMaxSpendingMoney()<calendarRecord.getPrice()){
                billModel.setMaxSpendingMoney(calendarRecord.getPrice());   //最高支出金额
                billModel.setMaxSpendingMoneyClass(calendarRecord.getIcon_Class());  //最高支出金额的类型
                billModel.setMaxSpendingMoneyTime(calendarRecord.getTime());  //最高支出金额的日期
                billModel.setMaxSpendingMoneyTime(calendarRecord.getTime());  //最高支出金额的类型
                billModel.setMaxSpendingMoneyNoteBookId(calendarRecord.getNoteBook());  //最高支出账本id
                NoteBook book= (NoteBook) new SqlOperation().SelectId(NoteBook.class,calendarRecord.getNoteBook());
                billModel.setMaxSpendingMoneyNoteBook(book.getNoteBookName());  //最高支出金额的账本
            }

        }else {
            sumIn++;
            billModel.setIncomeSumMoney(billModel.getIncomeSumMoney()+calendarRecord.getPrice()); //全部收入金额
            if (billModel.getMaxIncomeMoney()<calendarRecord.getPrice()){
                billModel.setMaxIncomeMoney(calendarRecord.getPrice());   //最高收入金额
                billModel.setMaxIncomeMoneyClass(calendarRecord.getIcon_Class());  //最高收入金额的类型
                billModel.setMaxIncomeMoneyTime(calendarRecord.getTime());  //最高收入金额的日期
                billModel.setMaxIncomeMoneyTime(calendarRecord.getTime());  //最高收入金额的类型
                billModel.setMaxIncomeMoneyNoteBookId(calendarRecord.getNoteBook());  //最高收入账本id
                NoteBook book= (NoteBook) new SqlOperation().SelectId(NoteBook.class,calendarRecord.getNoteBook());
                billModel.setMaxIncomeMoneyNoteBook(book.getNoteBookName());  //最高收入金额的账本
            }

        }




    }
    private void b(){
        List<String> strings1;
        List<String> strings2;
        if (NoteBookId!=-1){
            strings1=new SqlOperation().SelectSql("select count(icon_class) as A,icon_class as B from timeline where notebook=? and direction=1  group by icon_class;",NoteBookId+"");
            strings2=new SqlOperation().SelectSql("select count(icon_class) as A,icon_class as B from timeline where notebook=? and direction=0  group by icon_class;",NoteBookId+"");
        }else {
            strings1=new SqlOperation().SelectSql("select count(icon_class) as A,icon_class as B from timeline where  direction=1  group by icon_class;");
            strings2=new SqlOperation().SelectSql("select count(icon_class) as A,icon_class as B from timeline where  direction=0  group by icon_class;");
        }

        int classsum=0;
        int classid=0;
        for(String s:strings1){
            String[] strs=s.split("#");
            if (Integer.parseInt(strs[0])>classsum){
                classsum=Integer.parseInt(strs[0]);
                classid=Integer.parseInt(strs[1]);
            }
        }


        int classsum1=0;
        int classid1=0;
        for(String s:strings2){
            String[] strs=s.split("#");
            if (Integer.parseInt(strs[0])>classsum1){
                classsum1=Integer.parseInt(strs[0]);
                classid1=Integer.parseInt(strs[1]);
            }
        }


        billModel.setSpendingFrequencyClass(classid);  //支出次数最多的是什么
        billModel.setIncomeFrequencyClass(classid1);   //收入次数最多的是什么
    }
    private void c(){
        List<String> strings1;
        List<String> strings2;
        if (NoteBookId==-1){
            strings1=new SqlOperation().SelectSql("select sum(Price) as A,notebook as B from timeline where  direction=1  group by notebook;");
            strings2=new SqlOperation().SelectSql("select sum(Price) as A,notebook as B from timeline where  direction=0  group by notebook;");

            double Price=0;
            int NoteBookId=0;
            for(String s:strings1){
                String[] strs=s.split("#");
                if (Double.parseDouble(strs[0])>Price){
                    Price=Double.parseDouble(strs[0]);
                    NoteBookId=Integer.parseInt(strs[1]);
                }
            }


            double Price1=0;
            int NoteBookId1=0;
            for(String s:strings2){
                String[] strs=s.split("#");
                if (Double.parseDouble(strs[0])>Price1){
                    Price1=Double.parseDouble(strs[0]);
                    NoteBookId1=Integer.parseInt(strs[1]);
                }
            }

            NoteBook book= (NoteBook) new SqlOperation().SelectId(NoteBook.class,NoteBookId);
            if (book==null)
                billModel.setSpendingNoteBook("0次支出");  //支出最多的账本
            else
                billModel.setSpendingNoteBook(book.getNoteBookName());  //支出最多的账本

            NoteBook book1= (NoteBook) new SqlOperation().SelectId(NoteBook.class,NoteBookId1);

            if (book1==null)
                billModel.setIncomeNoteBook("0次收入");   //收入最多的账本
            else
                billModel.setIncomeNoteBook(book1.getNoteBookName());   //收入最多的账本
        }else {
            NoteBook book= (NoteBook) new SqlOperation().SelectId(NoteBook.class,NoteBookId);
            billModel.setSpendingNoteBook(book.getNoteBookName());  //支出最多的账本
            billModel.setIncomeNoteBook(book.getNoteBookName());   //收入最多的账本
        }
    }
}
