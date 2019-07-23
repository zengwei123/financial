package com.cnitpm.financial.Page.Activity.SearchList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.Model.Material;
import com.cnitpm.financial.R;

import java.util.List;


/**
 * Created by zengwei on 2019/1/23.
 */

public class SearchListActivity extends MvpActivity<SearchListPresenter> implements SearchListView {
    @ViewBind(R.id.SearchList_Back)
    private TextView SearchList_Back;  //返回按钮
    @ViewBind(R.id.SearchList_Edit)
    private EditText SearchList_Edit;   //文本框
    @ViewBind(R.id.SearchList_Recycler)
    private RecyclerView SearchList_Recycler;    //内容列表
    @ViewBind(R.id.SearchList_sort1)
    private TextView SearchList_sort1;   //综合
    @ViewBind(R.id.SearchList_sort2)
    private TextView SearchList_sort2;   //价格
    @ViewBind(R.id.SearchList_sort3)
    private TextView SearchList_sort3;   //销量
    @ViewBind(R.id.Searchlist_Checkbox)
    private CheckBox SearchList_Checkbox;   //是否又优惠券
    @ViewBind(R.id.Searchlist_Checkbox_Ticket)
    private TextView SearchList_Checkbox_Ticket;    //优惠券文字
    @ViewBind(R.id.SearchList_ExceptionLayout)
    private RelativeLayout SearchList_ExceptionLayout;   //网络错误 或者搜索没有的布局
    @ViewBind(R.id.SearchList_No)
    private ImageView SearchList_No;   //图片
    private Bundle bundle;
    private SearchListRecycler_Adapter searchListRecycler_adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlist);
        bundle=getIntent().getBundleExtra("KEY");
        mvpPresenter.attachView(this);
        super.injectViews();
        mvpPresenter.init();
    }

    @Override
    protected SearchListPresenter createPresenter() {
        return new SearchListPresenter();
    }

    @Override
    public TextView getSearchList_Back() {
        return SearchList_Back;
    }

    @Override
    public EditText getSearchList_Edit() {
        return SearchList_Edit;
    }

    @Override
    public RecyclerView getSearchList_Recycler() {
        return SearchList_Recycler;
    }

    @Override
    public TextView getSearchList_sort1() {
        return SearchList_sort1;
    }

    @Override
    public TextView getSearchList_sort2() {
        return SearchList_sort2;
    }

    @Override
    public TextView getSearchList_sort3() {
        return SearchList_sort3;
    }

    @Override
    public CheckBox getSearchList_Checkbox() {
        return SearchList_Checkbox;
    }

    @Override
    public TextView getSearchList_Checkbox_Ticket() {
        return SearchList_Checkbox_Ticket;
    }

    @Override
    public RelativeLayout getSearchList_ExceptionLayout() {
        return SearchList_ExceptionLayout;
    }

    @Override
    public ImageView getSearchList_No() {
        return SearchList_No;
    }

    @Override
    public Bundle getBundle() {
        return bundle;
    }

    @Override
    public void setSearchListRecycler_Adapter(Context context, List<Material> materials) {
       // searchListRecycler_adapter=new SearchListRecycler_Adapter(context,materials);
    }

    @Override
    public SearchListRecycler_Adapter getSearchListRecycler_Adapter() {
        return searchListRecycler_adapter;
    }

    @Override
    public SearchListActivity getSearchListActivity() {
        return this;
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Activity getThisActivity() {
        return this;
    }
}
