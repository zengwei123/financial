package com.cnitpm.financial.Page.Fragment.Main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.MvpFragment;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;

import com.cnitpm.financial.Custom.WaveView;
import com.cnitpm.financial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class MainFragment extends MvpFragment<MainPresenter> implements MainView {

    @ViewBind(R.id.Main_Recycler_TimeLine)
    private RecyclerView Main_Recycler_TimeLine;   //时光轴
    @ViewBind(R.id.Main_TextView_Money_L)
    private TextView Main_TextView_Money_L;     //本月收入
    @ViewBind(R.id.Main_TextView_Money_R)
    private TextView Main_TextView_Money_R;     //本月支出
    @ViewBind(R.id.Main_TextView_Date)
    private TextView Main_TextView_Date;   //今天的日期
    @ViewBind(R.id.Main_TextView_NoteBook)
    private TextView Main_TextView_NoteBook;   //账本
    @ViewBind(R.id.Main_TextView_Search)
    private TextView Main_TextView_Search;   //搜索
    @ViewBind(R.id.Main_TextView_Calendar)
    private TextView Main_TextView_Calendar;   //日历
    @ViewBind(R.id.Main_TextView_Budget)
    private TextView Main_TextView_Budget;
    @ViewBind(R.id.Main_Recycler_WavesView)
    private WaveView Main_Recycler_WavesView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_layout,null,false);
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
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public RecyclerView getMain_Recycler_TimeLine() {
        return Main_Recycler_TimeLine;
    }

    @Override
    public TextView getMain_TextView_Money_L() {
        return Main_TextView_Money_L;
    }

    @Override
    public TextView getMain_TextView_Money_R() {
        return Main_TextView_Money_R;
    }

    @Override
    public TextView getMain_TextView_Date() {
        return Main_TextView_Date;
    }

    @Override
    public TextView getMain_TextView_NoteBook() {
        return Main_TextView_NoteBook;
    }

    @Override
    public TextView getMain_TextView_Search() {
        return Main_TextView_Search;
    }

    @Override
    public TextView getMain_TextView_Calendar() {
        return Main_TextView_Calendar;
    }

    @Override
    public TextView getMain_TextView_Budget() {
        return Main_TextView_Budget;
    }

    @Override
    public WaveView getMain_Recycler_WavesView() {
        return Main_Recycler_WavesView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("zengwei123","kank");
        mvpPresenter.Refresh();
    }
}
