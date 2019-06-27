package com.cnitpm.financial.Base;

import android.app.Application;
import android.util.Log;

import org.litepal.LitePal;
import com.cnitpm.financial.Model.NoteBook;
import com.cnitpm.financial.Util.SharedPreferencesHelper;
import com.cnitpm.financial.Util.Utils;

import java.util.Date;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        LitePal.initialize(this);
        /**是否第一次进入**/
        boolean isOne= (boolean) new SharedPreferencesHelper(getApplicationContext(),"First").getSharedPreference("IS",true);
        if (isOne){
            new SharedPreferencesHelper(getApplicationContext(),"First").put("IS",false);
            new NoteBook("默认账本", Utils.getFormat("yyyy-MM-dd",new Date().getTime()),1000).save();
        }
    }
}
