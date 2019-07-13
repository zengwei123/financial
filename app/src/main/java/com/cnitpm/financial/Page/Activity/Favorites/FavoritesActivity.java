package com.cnitpm.financial.Page.Activity.Favorites;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnitpm.financial.Base.MvpActivity;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.Model.Material;
import com.cnitpm.financial.Page.Fragment.TaoKe.Home_Adapter;
import com.cnitpm.financial.R;

import java.util.List;

/**
 * Created by zengwei on 2019/2/2.
 * 选品库
 */

public class FavoritesActivity extends MvpActivity<FavoritesPresenter> implements FavoritesView{
    @ViewBind(R.id.Title_Back)
    private TextView Title_Back;
    @ViewBind(R.id.Title_Message)
    private TextView Title_Message;

    @ViewBind(R.id.Favorites_Recycler)
    private RecyclerView Favorites_Recycler;
    private com.cnitpm.financial.Page.Fragment.TaoKe.Home_Adapter Home_Adapter;

    private Bundle bundle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);
        bundle=getIntent().getBundleExtra("KEY");
        mvpPresenter.attachView(this);
        super.injectViews();
        mvpPresenter.init();
    }
    @Override
    protected FavoritesPresenter createPresenter() {
        return new FavoritesPresenter();
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
    public TextView getTitle_Back() {
        return Title_Back;
    }

    @Override
    public TextView getTitle_Message() {
        return Title_Message;
    }

    @Override
    public Bundle getBundle() {
        return bundle;
    }

    @Override
    public RecyclerView getFavorites_Recycler() {
        return Favorites_Recycler;
    }

    @Override
    public com.cnitpm.financial.Page.Fragment.TaoKe.Home_Adapter getHome_Adapter() {
        return Home_Adapter;
    }

    @Override
    public void setHome_Adapter(Context context, List<Material> materials) {
        Home_Adapter=new Home_Adapter(context,materials);
    }
}
