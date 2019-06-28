package com.cnitpm.financial.Page.Activity.Search;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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
import com.cnitpm.financial.Util.SqlOperation;
import com.cnitpm.financial.Util.Utils;

import java.util.Date;
import java.util.List;

public class SearchRecyclerAdapter extends BaseMultiItemQuickAdapter<AllModel, BaseViewHolder> {
    private Class c;
    private Context context;

    private List<AllModel> data;
    private Object object;
    public SearchRecyclerAdapter(Context context, Class c, List<AllModel> data) {
        super(data);
        this.c=c;
        this.context=context;
        this.data=data;
        if(c == TimeLine.class)
        {
            addItemType(1, R.layout.search_recycler_msg_item);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, AllModel item) {
        if(c == TimeLine.class)
        {
            TimeLine timeLine= (TimeLine) item.getData();
            Log.d("zengwei123",timeLine.toString());
            try {
                if(timeLine.getDirection()){
                    /**显示图标**/
                    Glide.with(context).load(Utils.LeftIcon[timeLine.getIcon_Class()]).into((ImageView) helper.getView(R.id.Search_RecyclerView_Icon));
                    /**显示分类**/
                    helper.setText(R.id.Search_RecyclerView_Title,Utils.LeftClass[timeLine.getIcon_Class()]);
                    /**收入**/
                    helper.setText(R.id.Search_RecyclerView_Price,"+"+timeLine.getPrice());
                    ((TextView)helper.getView(R.id.Search_RecyclerView_Price)).setTextColor(Color.parseColor("#ff0000"));
                }else {
                    Glide.with(context).load(Utils.RightIcon[timeLine.getIcon_Class()]).into((ImageView) helper.getView(R.id.Search_RecyclerView_Icon));
                    helper.setText(R.id.Search_RecyclerView_Title,Utils.RightClass[timeLine.getIcon_Class()]);
                    helper.setText(R.id.Search_RecyclerView_Price,"-"+timeLine.getPrice());
                    ((TextView)helper.getView(R.id.Search_RecyclerView_Price)).setTextColor(Color.parseColor("#2b2b2b"));
                }

                if(timeLine.getImageUrl()==null){
                    helper.getView(R.id.Search_RecyclerView_ImageUrl).setVisibility(View.GONE);
                }else {
                    helper.getView(R.id.Search_RecyclerView_ImageUrl).setVisibility(View.VISIBLE);
                    Glide.with(context).load(timeLine.getImageUrl()).into((ImageView) helper.getView(R.id.Search_RecyclerView_ImageUrl));
                }

                if(timeLine.getMessage().equals("")){
                    helper.getView(R.id.Search_RecyclerView_Message).setVisibility(View.GONE);
                }else {
                    helper.getView(R.id.Search_RecyclerView_Message).setVisibility(View.VISIBLE);
                    helper.setText(R.id.Search_RecyclerView_Message,"备注内容："+timeLine.getMessage());
                }

                NoteBook noteBook= (NoteBook) new SqlOperation().SelectId(NoteBook.class,timeLine.getNoteBook());
                helper.setText(R.id.Search_RecyclerView_NoteBook,noteBook.getNoteBookName());
                helper.setText(R.id.Search_RecyclerView_Time,timeLine.getTime());

            }catch (Exception e){
                /**出错说明是分割线  不进行任何操作**/
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
