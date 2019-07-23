package com.cnitpm.financial.Page.Activity.SearchList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.cnitpm.financial.Model.Material;

import java.util.List;

/**
 * Created by zengwei on 2019/1/23.
 */

public interface SearchListView extends BaseView {
    TextView getSearchList_Back();
    EditText getSearchList_Edit();
    RecyclerView getSearchList_Recycler();
    TextView getSearchList_sort1();
    TextView getSearchList_sort2();
    TextView getSearchList_sort3();
    CheckBox getSearchList_Checkbox();
    TextView getSearchList_Checkbox_Ticket();
    RelativeLayout getSearchList_ExceptionLayout();
    ImageView getSearchList_No();
    Bundle getBundle();
    void setSearchListRecycler_Adapter(Context context, List<Material> materials);
    SearchListRecycler_Adapter getSearchListRecycler_Adapter();
    SearchListActivity getSearchListActivity();
}
