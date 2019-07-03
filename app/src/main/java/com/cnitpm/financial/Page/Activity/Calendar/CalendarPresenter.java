package com.cnitpm.financial.Page.Activity.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.CalendarRecord;
import com.cnitpm.financial.Model.NoteBook;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.Page.Activity.AddRecord.AddRecordActivity;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.SqlOperation;
import com.cnitpm.financial.Util.UtilRecyclerAdapter;
import com.cnitpm.financial.Util.Utils;
import com.necer.entity.NDate;
import com.necer.listener.OnCalendarChangedListener;
import com.necer.painter.InnerPainter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarPresenter extends BasePresenter<CalendarView> {
    private NoteBook noteBook;
    private double daybalance=0;
    public String BTime=null;
    private List<AllModel> allModels=new ArrayList<>();
    @Override
    public void init() {
        noteBook= (NoteBook) mvpView.getBundle().getSerializable("key");
        getDaybalance(Integer.parseInt(Utils.getFormat("M",new Date().getTime())));
        chaochu(Utils.getFormat("yyyy-MM",new Date().getTime()));
        mvpView.getInclude_Back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpView.getThisActivity().finish();
            }
        });
        mvpView.getInclude_image().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("BTime",BTime);
                bundle.putInt("noteBookId",noteBook.getId());
                ((BaseActivity)mvpView.getThisActivity()).JumpBundleActivity(mvpView.getActivityContext(), AddRecordActivity.class,bundle,110);
            }
        });

        mvpView.getInclude_Title().setText("往日账单("+noteBook.getNoteBookName()+")");
        //当前的月份
        mvpView.getCalendar_TextView_YearMonth().setText(Utils.getFormat("yyyy年MM月",new Date().getTime()));
        //隐藏的按钮
        mvpView.getInclude_image().setVisibility(View.VISIBLE);


        /**显示超额支出日期**/
        DayRecord(Utils.getFormat("yyyy-MM-dd",new Date().getTime()));
        mvpView.getCalendar_Miui9Calendar().setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarDateChanged(NDate date, boolean isClick) {
                //获取日历当前的年月
                mvpView.getCalendar_TextView_YearMonth().setText(date.lunar.lunarYear+"年"+(date.lunar.lunarMonth+1)+"月");
                //日历回调 NDate包含公历、农历、节气、节假日、闰年等信息
                mvpView.getCalendar_TextView_YearMonth().setText(date.localDate.toString());
                DayRecord(date.localDate.toString());
                BTime=date.localDate.toString();
                mvpView.getCalendar_RecyclerView().getAdapter().notifyDataSetChanged();

            }
            @Override
            public void onCalendarStateChanged(boolean isMonthSate) {
                //日历状态回调， 月->周 isMonthSate返回false ，反之返回true
            }
        });

        /**每日查询**/
        mvpView.getCalendar_RecyclerView().setLayoutManager(new LinearLayoutManager(mvpView.getActivityContext()));
        mvpView.getCalendar_RecyclerView().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),CalendarRecord.class,allModels));
        /**控件删除事件**/
        ((UtilRecyclerAdapter)mvpView.getCalendar_RecyclerView().getAdapter()).setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.Calendar_RecyclerView_CalendarRecord_right:
                        new SqlOperation().DeleteSql(TimeLine.class,((CalendarRecord)allModels.get(position).getData()).getId());
                        chaochu(((CalendarRecord)allModels.get(position).getData()).getTime());
                        if(position!=allModels.size()-1){
                            allModels.remove(position+1);   //这里是删除最分割线
                        }
                        allModels.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.Calendar_RecyclerView_CalendarRecord_Content:
                        /**创建dialog**/
                        AlertDialog.Builder builder = new AlertDialog.Builder(mvpView.getActivityContext());
                        final AlertDialog dialog = builder.create();
                        /**dialogd的布局 ,每次打开大概增加0.5兆内存，无所谓啦 可以优化 但是我不干！**/
                        View dialogView = View.inflate(mvpView.getActivityContext(), R.layout.z_dialog_timeline_layout, null);
                        TextView Z_Dialog_TimeLine_Message=dialogView.findViewById(R.id.Z_Dialog_TimeLine_Message);
                        TextView Z_Dialog_TimeLine_Determine=dialogView.findViewById(R.id.Z_Dialog_TimeLine_Determine);
                        ImageView Z_Dialog_TimeLine_ImageView=dialogView.findViewById(R.id.Z_Dialog_TimeLine_ImageView);
                        String message=((CalendarRecord)allModels.get(position).getData()).getMessage();
                        String imageurl=((CalendarRecord)allModels.get(position).getData()).getImageUrl();
                        if(message.equals("")){
                            Z_Dialog_TimeLine_Message.setText("没有内容哦~");
                        }else {
                            Z_Dialog_TimeLine_Message.setText(message);
                        }
                        if(imageurl!=null&&!imageurl.equals("null")){
                            Glide.with(mvpView.getActivityContext()).load(imageurl).into(Z_Dialog_TimeLine_ImageView);
                        }else {
                            Glide.with(mvpView.getActivityContext()).load(R.mipmap.no_image).into(Z_Dialog_TimeLine_ImageView);
                        }
                        Z_Dialog_TimeLine_Determine.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });
                        dialog.setView(dialogView);
                        dialog.show();
                        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                        params.width = (int) Utils.getWindow(true,mvpView.getThisActivity())-100;
                        dialog.getWindow().setAttributes(params);
                        Toast.makeText(mvpView.getActivityContext(), "点击事件", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        /**添加空布局**/
        View view=  LayoutInflater.from(mvpView.getActivityContext()).inflate(R.layout.z_recycler_nodata_item, null);
        ((UtilRecyclerAdapter)mvpView.getCalendar_RecyclerView().getAdapter()).setEmptyView(view);
    }

    public void DayRecord(String time){
        allModels.clear();
        List<TimeLine> timeLines=new SqlOperation().SelectWhere(TimeLine.class,"Time=? and NoteBook=?",time,noteBook.getId()+"");
        for(int i=0;i<timeLines.size();i++){
            TimeLine timeLine=timeLines.get(i);
            Log.d("zengwei132",timeLine.toString());
            CalendarRecord calendarRecord=new CalendarRecord(timeLine.getId(),timeLine.getDirection()
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

    public void chaochu(String time){
        List<String> timeLines=new SqlOperation().SelectSql("select sum(Price) as A,time as B from timeline where time like ? and notebook=? and direction=1  group by time;",time+"%",noteBook.getId()+"");
        List<String> pointList=new ArrayList<>();
        for(String s:timeLines){
            String[] strs=s.split("#");
            if(Double.parseDouble(strs[0])>daybalance){
                pointList.add(strs[1]);
            }
        }
        InnerPainter innerPainter = (InnerPainter) mvpView.getCalendar_Miui9Calendar().getCalendarPainter();
        innerPainter.setPointList(pointList);
        mvpView.getCalendar_Miui9Calendar().notifyAllView();
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
