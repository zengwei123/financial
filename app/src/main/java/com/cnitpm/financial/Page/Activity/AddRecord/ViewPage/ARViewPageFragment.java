package com.cnitpm.financial.Page.Activity.AddRecord.ViewPage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnitpm.financial.Base.MvpFragment;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;

/**
 * 添加界面的viewpage 填充界面
 */
public class ARViewPageFragment extends MvpFragment<ARViewPagePresenter> implements ARViewPageView {
    @ViewBind(R.id.ARViewPager_Recycler_ClassChoose)
    private RecyclerView ARViewPager_Recycler_ClassChoose;
    private boolean isRL=true;

    private ARViewPageInterface arViewPageInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.arviewpage_layout,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter.attachView(this);
        injectViews(view);
        mvpPresenter.init();
    }

    @Override
    public Context getActivityContext() {
        return activity;
    }

    @Override
    public Activity getThisActivity() {
        return activity;
    }

    @Override
    protected ARViewPagePresenter createPresenter() {
        return new ARViewPagePresenter();
    }

    @Override
    public RecyclerView getARViewPager_Recycler_ClassChoose() {
        return ARViewPager_Recycler_ClassChoose;
    }

    @Override
    public boolean getIsRL() {
        return isRL;
    }

    @Override
    public ARViewPageInterface getARViewPageInterface() {
        return arViewPageInterface;
    }
    public void setArViewPageInterface(ARViewPageInterface arViewPageInterface) {
        this.arViewPageInterface = arViewPageInterface;
    }

    public void setRL(boolean RL) {
        isRL = RL;
    }
    public interface ARViewPageInterface{
        void ItemClick(int pos);
    }
}
