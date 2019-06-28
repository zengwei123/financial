package com.cnitpm.financial.Page.Activity.Search;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.cnitpm.financial.Custom.WanEditText;

public interface SearchView extends BaseView {
    ImageView getInclude_Back();
    TextView getInclude_Title();
    WanEditText getSearch_EditText_wan();
    RecyclerView getSearch_Recycler();
}
