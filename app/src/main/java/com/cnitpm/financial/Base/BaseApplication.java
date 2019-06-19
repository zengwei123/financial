package com.cnitpm.financial.Base;

import android.app.Application;

import org.litepal.LitePal;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        LitePal.initialize(this);
    }
}
