package com.cnitpm.financial.Base;

import android.os.Bundle;


import java.util.HashMap;
import java.util.Map;


/**
 * Created by zengwei on 2018/8/22.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity  {
    protected P mvpPresenter;
    protected abstract P createPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        hiddenBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            mvpPresenter =null;
        }
    }
}
