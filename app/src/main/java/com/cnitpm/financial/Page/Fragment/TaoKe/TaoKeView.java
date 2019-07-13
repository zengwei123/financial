package com.cnitpm.financial.Page.Fragment.TaoKe;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.cnitpm.financial.Model.Material;

import java.util.List;

public interface TaoKeView extends BaseView {
    EditText getHome_SearchEdit();
    ImageView getHome_Nine();
    ImageView getHome_Big();
    ImageView getHome_Good();
    ImageView getHome_Article();
    AppBarLayout getHome_AppBar();
    CollapsingToolbarLayout getHome_CollapsingToolbar();
    RecyclerView getHome_Recycler();
    FloatingActionButton getHome_FloatingActionButton();

    Home_Adapter getHome_Adapter();
    void setHome_Adapter(Context context, List<Material> materials);
}
