package com.cnitpm.financial.Page.Fragment.Main;


import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.cnitpm.financial.Custom.SpringbackView;
import com.cnitpm.financial.Custom.WaveView;

interface MainView extends BaseView {
    RecyclerView getMain_Recycler_TimeLine();
    TextView getMain_TextView_Money_L();
    TextView getMain_TextView_Money_R();
    TextView getMain_TextView_Date();
    TextView getMain_TextView_NoteBook();
    TextView getMain_TextView_Search();
    TextView getMain_TextView_Calendar();
    TextView getMain_TextView_Budget();
    WaveView getMain_Recycler_WavesView();
    RecyclerView getMain_RecyclerView_NoteBooks();
    LinearLayout getMain_LinearLayout_layouts();
    SpringbackView getMain_SpringbackView();
    void setNoteBookId(int i);
    int getNoteBookId();

}
