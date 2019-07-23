package com.cnitpm.financial.Page.Activity.HomeSearchEdit;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.cnitpm.financial.Custom.HotLayout;


/**
 * Created by zengwei on 2019/1/19.
 */
public interface HomeSearchEditView extends BaseView {
    TextView getHomeSearchEdit_Back();
    EditText getHomeSearchEdit_Edit();
    HotLayout getHomeSearchEdit_HotLayout();
    TextView getHomeSearchEdit_Delete();
    TextView getHomeSearchEdit_Search();
    Bundle getBundle();
    HomeSearchEditActivity getHomeSearchEditActivity();
}
