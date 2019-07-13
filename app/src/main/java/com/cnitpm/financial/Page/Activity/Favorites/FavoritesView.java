package com.cnitpm.financial.Page.Activity.Favorites;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.cnitpm.financial.Model.Material;
import com.cnitpm.financial.Page.Fragment.TaoKe.Home_Adapter;

import java.util.List;

/**
 * Created by zengwei on 2019/2/2.
 */

public interface FavoritesView extends BaseView {
    TextView getTitle_Back();
    TextView getTitle_Message();
    Bundle getBundle();
    RecyclerView getFavorites_Recycler();

    Home_Adapter getHome_Adapter();
    void setHome_Adapter(Context context, List<Material> materials);
}
