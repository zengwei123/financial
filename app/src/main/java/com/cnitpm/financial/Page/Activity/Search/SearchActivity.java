package com.cnitpm.financial.Page.Activity.Search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.Custom.WanEditText;
import com.cnitpm.financial.R;

public class SearchActivity extends MvpActivity<SearchPresenter> implements SearchView {
    @ViewBind(R.id.Include_Back)
    private ImageView Include_Back;   //返回
    @ViewBind(R.id.Include_Title)
    private TextView textView;     //标题
    @ViewBind(R.id.Search_EditText_wan)
    private WanEditText Search_EditText_wan;  //输入法
    @ViewBind(R.id.Search_Recycler)
    private RecyclerView Search_Recycler;   //查询的列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        mvpPresenter.attachView(this);
        super.injectViews();
        mvpPresenter.init();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Activity getThisActivity() {
        return this;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public ImageView getInclude_Back() {
        return Include_Back;
    }

    @Override
    public TextView getInclude_Title() {
        return textView;
    }

    @Override
    public WanEditText getSearch_EditText_wan() {
        return Search_EditText_wan;
    }

    @Override
    public RecyclerView getSearch_Recycler() {
        return Search_Recycler;
    }
}
