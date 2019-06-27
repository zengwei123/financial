package com.cnitpm.financial.Page.Activity.Search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;

public class SearchActivity extends MvpActivity<SearchPresenter> implements SearchView {
    @ViewBind(R.id.Include_Back)
    private ImageView Include_Back;   //返回
    @ViewBind(R.id.Include_Title)
    private TextView textView;     //标题
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
}
