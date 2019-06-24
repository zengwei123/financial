package com.cnitpm.financial.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * activity基类
 */
public class BaseActivity extends AppCompatActivity {
    private static Intent intent;
    public static List<BaseActivity> baseActivities; //关闭activity
    private long exitTime = 0;

    /**
     * 判断网络是否可用
     * @param context
     * @return
     */
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }
        return true;
    }


    /**
     * 权限检查方法，false代表没有该权限，ture代表有该权限
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限请求方法
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
    }

    /**
     * 双击退出程序
     * @return
     */
    public void OnTwoBack() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - exitTime > 2000) {
            Toast.makeText(BaseActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = secondTime;
        } else{
            finishAllActivity();
        }
    }

    /**
     * 设置沉浸式效果
     */
    public void immersionWindow(){
        // 沉浸效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 隐藏标题栏
     */
    public void hiddenBar(){
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }

    /**
     * 添加Activity
     * @param baseActivity
     */
    public void addActivity(BaseActivity baseActivity){
        if (baseActivities==null){
            baseActivities=new ArrayList<>();
        }
        baseActivities.add(baseActivity);
    }

    /**
     * 关闭所有Activity
     */
    public void finishAllActivity(){
        try {
            for (BaseActivity activity:baseActivities){
                if (activity!=null){
                    activity.finish();
                }
            }
        }catch (Exception e){
            this.finish();
        }
    }

    /**
     * 移除Activity
     */
    public void removeActivity(Activity activity) {
        if (baseActivities != null)
            baseActivities.remove(activity);
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
    /**
     * 传参数跳转activity 方法
     * @param context
     * @param iclass
     */
    public void JumpBundleActivity(Context context, Class iclass,Bundle bundle,int code){
        intent=new Intent(context,iclass);
        intent.putExtra("KEY",bundle);
        startActivityForResult(intent,code);
    }
    /**控件绑定关键方法**/
    protected void injectViews(){
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewBind.class)) {
                ViewBind inject = field.getAnnotation(ViewBind.class);
                int id = inject.value();
                if (id > 0) {
                    field.setAccessible(true);
                    try {
                        field.set(this, this.findViewById(id));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
