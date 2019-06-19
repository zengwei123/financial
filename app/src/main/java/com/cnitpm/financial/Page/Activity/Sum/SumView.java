package com.cnitpm.financial.Page.Activity.Sum;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.cnitpm.financial.Base.BaseView;

interface SumView extends BaseView {
    ViewPager getSum_ViewPager_Page();
    TabLayout getSum_TabLayout_Menu();
}
