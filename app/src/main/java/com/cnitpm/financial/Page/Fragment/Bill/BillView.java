package com.cnitpm.financial.Page.Fragment.Bill;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;

public interface BillView extends BaseView {
    RelativeLayout getBill_Add_Layout();
    Button getBill_Add_Button();
    TextView getBill_Initial_Text();
    TextView getBill_End_Text();
    TextView getBill_Generate();
}
