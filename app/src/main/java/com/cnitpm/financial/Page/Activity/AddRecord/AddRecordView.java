package com.cnitpm.financial.Page.Activity.AddRecord;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;

public interface AddRecordView extends BaseView {
    ImageView getInclude_Back();
    TextView getInclude_Title();

    RecyclerView getAddRecord_RecyclerView_Calculator();
    TextView getAddRecord_TextView_Sum();
    TextView getAddRecord_TextView_Symbol();
    TabLayout getAddRecord_TabLayout_LR();

    ViewPager getAddRecord_ViewPager_LR();
    ImageView getAddRecord_ImageView_Icon();
    TextView getAddRecord_TextView_Text();

    TextView getAddRecord_TextView_MessageTime();
    TextView getAddRecord_TextView_AddMessage();
    TextView getAddRecord_TextView_NoteBook();

    RelativeLayout getAddRecord_RelativeLayout_layout();
    ImageView getAddRecord_ImageView_Camera();
    EditText getAddRecord_EditText_Message();


    TextView getAddRecord_TextView_Cancel();
    TextView getAddRecord_TextView_Determine();

    String getImageUrl();
}
