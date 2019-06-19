package com.cnitpm.financial.Page.Activity.AddRecord.ViewPage;

import android.support.v7.widget.RecyclerView;

import com.cnitpm.financial.Base.BaseView;

public interface ARViewPageView extends BaseView {
    RecyclerView getARViewPager_Recycler_ClassChoose();
    boolean getIsRL();
    ARViewPageFragment.ARViewPageInterface getARViewPageInterface();
}
