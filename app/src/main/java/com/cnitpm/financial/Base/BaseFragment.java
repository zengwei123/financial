package com.cnitpm.financial.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;


import java.lang.reflect.Field;

/**
 * Created by zengwei on 2018/8/23.
 */

public class BaseFragment extends Fragment {
    private static Intent intent;
    public Activity activity;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity=getActivity();
    }

    /**
     * 跳转activity 方法
     * @param context
     * @param iclass
     */
    public void JumpActivity(Context context, Class iclass){
        intent=new Intent(context,iclass);
        startActivity(intent);
    }
    /**
     * 传参数跳转activity 方法
     * @param context
     * @param iclass
     */
    public void JumpBundleActivity(Context context, Class iclass,Bundle bundle){
        intent=new Intent(context,iclass);
        intent.putExtra("KEY",bundle);
        startActivity(intent);
    }

    /**控件绑定**/
    public void injectViews(View view){
        Field[] fields=this.getClass().getDeclaredFields();
        for (Field field:fields){
            if (field.isAnnotationPresent(ViewBind.class)) {
                ViewBind inject=field.getAnnotation(ViewBind.class);
                int id = inject.value();
                if (id>0){
                    field.setAccessible(true);
                    try {
                        field.set(this,view.findViewById(id));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
