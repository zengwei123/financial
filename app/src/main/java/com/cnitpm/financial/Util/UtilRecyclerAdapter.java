package com.cnitpm.financial.Util;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.CIModel;
import com.cnitpm.financial.Model.CalendarRecord;
import com.cnitpm.financial.Model.NoteBook;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.R;

import java.util.Date;
import java.util.List;

public class UtilRecyclerAdapter extends BaseMultiItemQuickAdapter<AllModel, BaseViewHolder> {
    private Class c;
    private Context context;

    private List<AllModel> data;
    private Object object;
    public UtilRecyclerAdapter(Context context, Class c, List<AllModel> data) {
        super(data);
        this.c=c;
        this.context=context;
        this.data=data;
        if(c == TimeLine.class)  /**首页时光轴布局**/
        {
            addItemType(1, R.layout.main_recycler_timeline_item);
        }
        else if(c == CalendarRecord.class)   /**日历--历史记录  列表**/
        {
            addItemType(1, R.layout.calendar_recycler_calendarrecord_item);
            addItemType(2, R.layout.z_recycler_divider_item);
        }
        else if(c == String.class)   /**添加记账 计算器 算盘**/
        {
            addItemType(1, R.layout.addrecord_recycler_calculator_item);
        }
        else if(c== CIModel.class){   /**添加界面的类型列表**/
            addItemType(1, R.layout.arviewpage_recycler_classcloose_item);
        }
    }

    public UtilRecyclerAdapter(Context context, Class c, List<AllModel> data,Object i) {
        super(data);
        this.c=c;
        this.context=context;
        this.data=data;
        this.object=i;
        if(c== NoteBook.class){  /**账本**/
            addItemType(1, R.layout.addrecord_recycler_notebooks_item);
            addItemType(2, R.layout.addrecord_recycler_notebooks_add_item);
        }
    }


    @Override
    protected void convert(BaseViewHolder helper, AllModel item) {
        if(c == TimeLine.class)  /**首页时光轴布局**/
        {
            TimeLine timeLine= (TimeLine) item.getData();
            helper.getView(R.id.Main_Recycler_TimeLine_Item_TimeLayout).setVisibility(View.GONE);
            try {
                /**这里判断上一笔消费 是否与这一笔消费一样  如果报错说明是第一笔**/
                String s=((TimeLine)data.get(helper.getAdapterPosition()-1).getData()).getTime();
                if(!timeLine.getTime().equals(s)){
                    helper.getView(R.id.Main_Recycler_TimeLine_Item_TimeLayout).setVisibility(View.VISIBLE);
                    helper.setText(R.id.Main_Recycler_TimeLine_Item_Title_Time_L,Utils.getFormat("yyyy",new Date().getTime()));
                    helper.setText(R.id.Main_Recycler_TimeLine_Item_Title_Time_R,Utils.getFormat("MM-dd",new Date().getTime()));
                }
            }catch (Exception e){
                helper.getView(R.id.Main_Recycler_TimeLine_Item_TimeLayout).setVisibility(View.VISIBLE);
                helper.setText(R.id.Main_Recycler_TimeLine_Item_Title_Time_L,Utils.getFormat("yyyy",new Date().getTime()));
                helper.setText(R.id.Main_Recycler_TimeLine_Item_Title_Time_R,Utils.getFormat("MM-dd",new Date().getTime()));
            }

            /**隐藏全部信息布局 防止recycleView 复用item 造成显示错乱**/
            helper.getView(R.id.Main_Recycler_TimeLine_Item_Layout_R).setVisibility(View.GONE);
            helper.getView(R.id.Main_Recycler_TimeLine_Item_Layout_L).setVisibility(View.GONE);
            /**加载中间的图片**/
            if(timeLine.getDirection()){   /**这边是收入的**/
                Glide.with(context).load(Utils.LeftIcon[timeLine.getIcon_Class()]).into((ImageView) helper.getView(R.id.Main_Recycler_TimeLine_Item_Icon));
                /**消费类别和金额**/
                SpannableString spannableString = new SpannableString(Utils.LeftClass[timeLine.getIcon_Class()]+"：￥"+timeLine.getPrice());
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
                spannableString.setSpan(colorSpan, 0, Utils.LeftClass[timeLine.getIcon_Class()].length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                helper.setText(R.id.Main_Recycler_TimeLine_Item_Title_L,spannableString);

                if(timeLine.getMessage()!=null&&!timeLine.getMessage().equals("")){
                    helper.setText(R.id.Main_Recycler_TimeLine_Item_Message_L,timeLine.getMessage());
                    helper.getView(R.id.Main_Recycler_TimeLine_Item_Message_L).setVisibility(View.VISIBLE);
                }
                helper.getView(R.id.Main_Recycler_TimeLine_Item_Layout_L).setVisibility(View.VISIBLE);
            }else {/**这边是支出的**/
                Glide.with(context).load(Utils.RightIcon[timeLine.getIcon_Class()]).into((ImageView) helper.getView(R.id.Main_Recycler_TimeLine_Item_Icon));
                /**消费类别和金额**/
                SpannableString spannableString = new SpannableString(Utils.RightClass[timeLine.getIcon_Class()]+"：￥"+timeLine.getPrice());
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
                spannableString.setSpan(colorSpan, 0, Utils.RightClass[timeLine.getIcon_Class()].length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                helper.setText(R.id.Main_Recycler_TimeLine_Item_Title_R,spannableString);
                /**判断有没有备注，有备注就显示，没有就隐藏**/
                if(timeLine.getMessage()!=null&&!timeLine.getMessage().equals("")){
                    helper.setText(R.id.Main_Recycler_TimeLine_Item_Message_R,timeLine.getMessage());
                    helper.getView(R.id.Main_Recycler_TimeLine_Item_Message_R).setVisibility(View.VISIBLE);
                }
                helper.getView(R.id.Main_Recycler_TimeLine_Item_Layout_R).setVisibility(View.VISIBLE);
            }
        }
/************************************************************************************************************************************************/
        else if(c == CalendarRecord.class) /**日历列表数据显示**/
        {
            CalendarRecord calendarRecord= (CalendarRecord) item.getData();
            try {
                if(calendarRecord.getDirection()){
                    /**显示图标**/
                    Glide.with(context).load(Utils.LeftIcon[calendarRecord.getIcon_Class()]).into((ImageView) helper.getView(R.id.Calendar_RecyclerView_CalendarRecord_Icon));
                    /**显示分类**/
                    helper.setText(R.id.Calendar_RecyclerView_CalendarRecord_Title,Utils.LeftClass[calendarRecord.getIcon_Class()]);
                    /**收入**/
                    helper.setText(R.id.Calendar_RecyclerView_CalendarRecord_Price,"+"+calendarRecord.getPrice());
                    ((TextView)helper.getView(R.id.Calendar_RecyclerView_CalendarRecord_Price)).setTextColor(Color.parseColor("#ff0000"));
                }else {
                    Glide.with(context).load(Utils.RightIcon[calendarRecord.getIcon_Class()]).into((ImageView) helper.getView(R.id.Calendar_RecyclerView_CalendarRecord_Icon));
                    helper.setText(R.id.Calendar_RecyclerView_CalendarRecord_Title,Utils.RightClass[calendarRecord.getIcon_Class()]);
                    helper.setText(R.id.Calendar_RecyclerView_CalendarRecord_Price,"-"+calendarRecord.getPrice());
                    ((TextView)helper.getView(R.id.Calendar_RecyclerView_CalendarRecord_Price)).setTextColor(Color.parseColor("#2b2b2b"));
                }
                //删除按钮 删除删除删除
                helper.addOnClickListener(R.id.Calendar_RecyclerView_CalendarRecord_right);
                helper.addOnClickListener(R.id.Calendar_RecyclerView_CalendarRecord_Content);
            }catch (Exception e){
                /**出错说明是分割线  不进行任何操作**/
            }

        }
/**********************************************************************************************************************************************/
        else if(c == String.class) /**添加记账的那个 计算器 算盘**/
        {
            try {
                helper.setText(R.id.AddRecord_RecyclerView_Calculator_Item_Text,(String)item.getData());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
/**********************************************************************************************************************************************/
        else if(c == CIModel.class) /**添加记录的 类型选择**/
        {
            try {
                Glide.with(context).load(((CIModel)item.getData()).getIcon()).into((ImageView) helper.getView(R.id.ARViewPager_Recycler_ClassChoose_Item_Image));
                helper.setText(R.id.ARViewPager_Recycler_ClassChoose_Item_Text,((CIModel)item.getData()).getAclass());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
/**********************************************************************************************************************************************/
        else if(c == NoteBook.class) /**账本**/
        {
            try {
                if(item.getItemType()==1){
                    if((int)object==helper.getAdapterPosition()){
                        Glide.with(context).load(R.mipmap.notebook_bg1).into((ImageView) helper.getView(R.id.AddRecord_RecyclerView_NoteBooks_Image));
                    }else {
                        Glide.with(context).load(R.mipmap.notebook_bg).into((ImageView) helper.getView(R.id.AddRecord_RecyclerView_NoteBooks_Image));
                    }
                    helper.setText(R.id.AddRecord_RecyclerView_NoteBooks_Text,((NoteBook)item.getData()).getNoteBookName());
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
