package com.cnitpm.financial.Page.Fragment.Main;


import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;

interface MainView extends BaseView {
    RecyclerView getMain_Recycler_TimeLine();
    TextView getMain_TextView_Money_L();
    TextView getMain_TextView_Money_R();
    TextView getMain_TextView_Date();
    TextView getMain_TextView_NoteBook();
    TextView getMain_TextView_Search();
    TextView getMain_TextView_Calendar();
    TextView getMain_TextView_Budget();
}
