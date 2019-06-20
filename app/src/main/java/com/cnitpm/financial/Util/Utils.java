package com.cnitpm.financial.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

import com.cnitpm.financial.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Utils {
    /**中间的图标
     *
     * R.mipmap.spending_41, R.mipmap.spending_42,
     * R.mipmap.spending_43, R.mipmap.spending_44, R.mipmap.spending_45, R.mipmap.spending_46, R.mipmap.spending_47, R.mipmap.spending_48,
     * R.mipmap.spending_49, R.mipmap.spending_50
     *
     * **/
    /**收入的类型**/
    public static String[] LeftClass={"工资","生活费","生意经营","收礼","红包","零花钱","兼职外快","投资盈利","奖金","报销","退款","彩票","借入"," 存款利息","其他"};

    public static int[] LeftIcon={R.mipmap.spending_1, R.mipmap.spending_2, R.mipmap.spending_3, R.mipmap.spending_4, R.mipmap.spending_5, R.mipmap.spending_6,
            R.mipmap.spending_7, R.mipmap.spending_8, R.mipmap.spending_9, R.mipmap.spending_10, R.mipmap.spending_11, R.mipmap.spending_12,
            R.mipmap.spending_13, R.mipmap.spending_14, R.mipmap.spending_15};


    /**支出的类型**/
    public static String[] RightClass={"日常三餐","玩乐三餐","交通","酒水饮料","水果","零食","衣服鞋包","生活用品","话费","电子产品","知识付费",
            "护肤彩妆","水电燃气","房租","电影","网上购物","发红包", "药品","送礼","借出","还借","投资亏损","贷款","花呗","其他"};

    public static int[] RightIcon={R.mipmap.spending_16, R.mipmap.spending_17, R.mipmap.spending_18,
            R.mipmap.spending_19, R.mipmap.spending_20, R.mipmap.spending_21, R.mipmap.spending_22, R.mipmap.spending_23, R.mipmap.spending_24,
            R.mipmap.spending_25, R.mipmap.spending_26, R.mipmap.spending_27, R.mipmap.spending_28, R.mipmap.spending_29, R.mipmap.spending_30,
            R.mipmap.spending_31, R.mipmap.spending_32, R.mipmap.spending_33, R.mipmap.spending_34, R.mipmap.spending_35, R.mipmap.spending_36,
            R.mipmap.spending_37, R.mipmap.spending_38, R.mipmap.spending_39, R.mipmap.spending_40};

    /**时间格式化**/
    public static String getFormat(String str,long time){
        Date date=new Date(time);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat (str);
        return simpleDateFormat.format(date);
    }

    /**字体图库设置**/
    public static Typeface getTypeFace(Context context){
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"font/icomoon.ttf");
        return typeface;
    }
    /**获得屏幕的宽高**/
    public static float getWindow(boolean b, Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        if(b){
            return dm.widthPixels;
        }else {
            return dm.heightPixels;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
