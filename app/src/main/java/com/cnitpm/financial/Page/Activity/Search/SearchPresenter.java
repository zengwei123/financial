package com.cnitpm.financial.Page.Activity.Search;

import android.os.Bundle;
import android.view.View;

import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Page.Activity.AddRecord.AddRecordActivity;

public class SearchPresenter extends BasePresenter<SearchView> {
    @Override
    public void init() {
        mvpView.getInclude_Back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpView.getThisActivity().finish();
            }
        });
        mvpView.getInclude_Title().setText("记录搜索");
    }
}
