package com.cnitpm.financial.Page.Activity.Sum;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;

public class SumActivity extends MvpActivity<SumPresenter> implements SumView{
    @ViewBind(R.id.Sum_ViewPager_Page)
    private ViewPager Sum_ViewPager_Page;
    @ViewBind(R.id.Sum_TabLayout_Menu)
    private TabLayout Sum_TabLayout_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sum_layout);
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
    protected SumPresenter createPresenter() {
        return new SumPresenter();
    }

    @Override
    public ViewPager getSum_ViewPager_Page() {
        return Sum_ViewPager_Page;
    }

    @Override
    public TabLayout getSum_TabLayout_Menu() {
        return Sum_TabLayout_Menu;
    }
}
