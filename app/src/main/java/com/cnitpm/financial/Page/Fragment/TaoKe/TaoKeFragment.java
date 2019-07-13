package com.cnitpm.financial.Page.Fragment.TaoKe;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.financial.Base.MvpFragment;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.Model.Material;
import com.cnitpm.financial.R;

import java.util.List;

public class TaoKeFragment extends MvpFragment<TaoKePresenter> implements TaoKeView {
    @ViewBind(R.id.Home_SearchEdit)
    private EditText Home_SearchEdit;   //搜索框
    @ViewBind(R.id.Home_Nine)
    private ImageView Home_Nine;    //9.9b包邮
    @ViewBind(R.id.Home_Big)
    private ImageView Home_Big;    //大额券
    @ViewBind(R.id.Home_Good)
    private ImageView Home_Good;    //精品推荐
    @ViewBind(R.id.Home_Article)
    private ImageView Home_Article;     //美物分享
    @ViewBind(R.id.Home_AppBar)
    private AppBarLayout Home_AppBar;
    @ViewBind(R.id.Home_CollapsingToolbar)
    private CollapsingToolbarLayout Home_CollapsingToolbar;
    @ViewBind(R.id.Home_Recycler)
    private RecyclerView Home_Recycler;
    @ViewBind(R.id.Home_FloatingActionButton)
    private FloatingActionButton Home_FloatingActionButton;

    private Home_Adapter Home_Adapter;;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.taoke_layout,null,false);
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
    protected TaoKePresenter createPresenter() {
        return new TaoKePresenter();
    }

    @Override
    public EditText getHome_SearchEdit() {
        return Home_SearchEdit;
    }

    @Override
    public ImageView getHome_Nine() {
        return Home_Nine;
    }

    @Override
    public ImageView getHome_Big() {
        return Home_Big;
    }

    @Override
    public ImageView getHome_Good() {
        return Home_Good;
    }

    @Override
    public ImageView getHome_Article() {
        return Home_Article;
    }

    @Override
    public AppBarLayout getHome_AppBar() {
        return Home_AppBar;
    }

    @Override
    public CollapsingToolbarLayout getHome_CollapsingToolbar() {
        return Home_CollapsingToolbar;
    }

    @Override
    public RecyclerView getHome_Recycler() {
        return Home_Recycler;
    }

    @Override
    public FloatingActionButton getHome_FloatingActionButton() {
        return Home_FloatingActionButton;
    }

    @Override
    public Home_Adapter getHome_Adapter() {
        return Home_Adapter;
    }

    @Override
    public void setHome_Adapter(Context context, List<Material> materials) {
        Home_Adapter=new Home_Adapter(context,materials);
    }
}
