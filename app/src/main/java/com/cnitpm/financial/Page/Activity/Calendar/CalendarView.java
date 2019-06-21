package com.cnitpm.financial.Page.Activity.Calendar;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnitpm.financial.Base.BaseView;
import com.necer.calendar.Miui9Calendar;

public interface CalendarView extends BaseView {
    ImageView getInclude_Back();
    TextView getInclude_Title();
    TextView getCalendar_TextView_YearMonth();
    Miui9Calendar getCalendar_Miui9Calendar();
    ImageView getInclude_image();
    RecyclerView getCalendar_RecyclerView();
    Bundle getBundle();
}
