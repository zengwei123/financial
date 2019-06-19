package com.cnitpm.financial.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;
    protected abstract P createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter=createPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter!=null){
            mvpPresenter.detachView();
            mvpPresenter=null;
        }
    }
}
