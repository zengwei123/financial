package com.cnitpm.financial.Page.Activity.HomeSearchEdit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.Custom.HotLayout;
import com.cnitpm.financial.R;

/**
 * Created by zengwei on 2019/1/19.
 * 搜索界面
 */

public class HomeSearchEditActivity extends MvpActivity<HomeSearchEditPresenter> implements HomeSearchEditView {
    @ViewBind(R.id.HomeSearchEdit_Back)
    private TextView HomeSearchEdit_Back;
    @ViewBind(R.id.HomeSearchEdit_Edit)
    private EditText HomeSearchEdit_Edit;
    @ViewBind(R.id.HomeSearchEdit_HotLayout)
    private HotLayout HomeSearchEdit_HotLayout;
    @ViewBind(R.id.HomeSearchEdit_Delete)
    private TextView HomeSearchEdit_Delete;
    @ViewBind(R.id.HomeSearchEdit_Search)
    private TextView HomeSearchEdit_Search;
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homesearchedit);
        bundle=getIntent().getExtras();
        mvpPresenter.attachView(this);
        super.injectViews();
        mvpPresenter.init();
    }

    @Override
    protected HomeSearchEditPresenter createPresenter() {
        return new HomeSearchEditPresenter();
    }

    @Override
    public TextView getHomeSearchEdit_Back() {
        return HomeSearchEdit_Back;
    }

    @Override
    public EditText getHomeSearchEdit_Edit() {
        return HomeSearchEdit_Edit;
    }

    @Override
    public HotLayout getHomeSearchEdit_HotLayout() {
        return HomeSearchEdit_HotLayout;
    }

    @Override
    public TextView getHomeSearchEdit_Delete() {
        return HomeSearchEdit_Delete;
    }

    @Override
    public TextView getHomeSearchEdit_Search() {
        return HomeSearchEdit_Search;
    }

    @Override
    public Bundle getBundle() {
        return bundle;
    }

    @Override
    public HomeSearchEditActivity getHomeSearchEditActivity() {
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
