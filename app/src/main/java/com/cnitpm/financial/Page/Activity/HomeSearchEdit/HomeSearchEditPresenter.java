package com.cnitpm.financial.Page.Activity.HomeSearchEdit;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Custom.MSGDialogView;
import com.cnitpm.financial.Page.Activity.SearchList.SearchListActivity;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.SharedPreferencesHelper;
import com.cnitpm.financial.Util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zengwei on 2019/1/19.
 */

public class HomeSearchEditPresenter extends BasePresenter<HomeSearchEditView> implements View.OnClickListener{
    private List<String> mLabels =new ArrayList<>();
    @Override
    public void init() {
        setHomeSearchEdit_Back();
        setHomeSearchEdit_Edit();
        setHomeSearchEdit_HotLayout();
        setHomeSearchEdit_Delete();
        //搜索按钮的点击事件
        mvpView.getHomeSearchEdit_Search().setOnClickListener(this);
    }
    //返回按钮
    private void setHomeSearchEdit_Back(){
        mvpView.getHomeSearchEdit_Back().setTypeface(Utils.getTypeFace(mvpView.getHomeSearchEditActivity()));
        mvpView.getHomeSearchEdit_Back().setText("\ueac3");
        mvpView.getHomeSearchEdit_Back().setOnClickListener(this);
    }
    //文本框
    private void setHomeSearchEdit_Edit(){
        if(mvpView.getBundle()!=null){
            mvpView.getHomeSearchEdit_Edit().setText(mvpView.getBundle().getString("value"));
        }
        mvpView.getHomeSearchEdit_Edit().setTypeface(Utils.getTypeFace(mvpView.getHomeSearchEditActivity()));
        mvpView.getHomeSearchEdit_Edit().setHint("\uecb3 输入关键字或商品标题");
        /**获取焦点  并且弹出键盘**/
        mvpView.getHomeSearchEdit_Edit().setFocusable(true);
        mvpView.getHomeSearchEdit_Edit().setFocusableInTouchMode(true);
        mvpView.getHomeSearchEdit_Edit().requestFocus();
        mvpView.getHomeSearchEditActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //键盘的搜索按钮
        mvpView.getHomeSearchEdit_Edit().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH ) {
                    S();
                    return true;
                }
                return false;
            }
        });
    }
    //删除所有搜索记录
    private void setHomeSearchEdit_Delete(){
        mvpView.getHomeSearchEdit_Delete().setTypeface(Utils.getTypeFace(mvpView.getHomeSearchEditActivity()));
        mvpView.getHomeSearchEdit_Delete().setText("\ue872");
        mvpView.getHomeSearchEdit_Delete().setOnClickListener(this);
    }
    //热门控件
    private void setHomeSearchEdit_HotLayout(){
        //清除原来的集合数据
        mLabels.clear();
        //获取搜索记录
        Map<String,String> map= (Map<String, String>) new SharedPreferencesHelper(mvpView.getHomeSearchEditActivity().getApplicationContext(),"SearchRecord").getAll();
        for(String s:map.keySet()){
            mLabels.add(map.get(s));
        }
        //balalal
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 35, 20, 10);// 设置边距
        for (int i = 0; i < mLabels.size(); i++) {
            final TextView textView = new TextView(mvpView.getHomeSearchEditActivity());
            textView.setTag(i);
            textView.setTextSize(15);
            textView.setText(mLabels.get(i));
            textView.setPadding(24, 11, 24, 11);
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setBackgroundResource(R.drawable.homesearchedit_hottext);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setSingleLine(true);
            mvpView.getHomeSearchEdit_HotLayout().addView(textView, layoutParams);
            // 标签点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //这里应该输入搜索
                    mvpView.getHomeSearchEdit_Edit().setText(mLabels.get((int) textView.getTag()));
                    SEARCH( mvpView.getHomeSearchEdit_Edit().getText().toString().trim());
                }
            });
        }
    }
    /**搜索事件的方法体**/
    private void SEARCH(String str){
        Bundle bundle=new Bundle();
        bundle.putString("value",str);
        mvpView.getHomeSearchEditActivity().JumpBundleActivity(mvpView.getHomeSearchEditActivity(),SearchListActivity.class,bundle);
        mvpView.getHomeSearchEditActivity().finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.HomeSearchEdit_Delete:
                MSGDialogView msgDialogView=new MSGDialogView();
                /**对话框**/
                final Dialog dialog= msgDialogView.getDialogView(mvpView.getThisActivity());
                msgDialogView.getDialogView_View_Message().setText("是否删除历史搜索记录？");
                //取消
                msgDialogView.getDialogView_View_Cancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //确认
                msgDialogView.getDialogView_View_Confirm().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new SharedPreferencesHelper(mvpView.getHomeSearchEditActivity().getApplicationContext(),"SearchRecord").clear();
                        mvpView.getHomeSearchEdit_HotLayout().removeAllViews();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.HomeSearchEdit_Search:
                S();
                break;
            case R.id.HomeSearchEdit_Back:
                mvpView.getHomeSearchEditActivity().finish();
                break;
        }
    }
    private void S(){
        String EditString=mvpView.getHomeSearchEdit_Edit().getText().toString().trim();
        if(mvpView.getHomeSearchEdit_Edit().getText().toString().trim().equals("")){
            Toast.makeText(mvpView.getHomeSearchEditActivity(), "请输入内容再搜索", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(mvpView.getHomeSearchEditActivity().getApplicationContext(),"SearchRecord");
            if(sharedPreferencesHelper.getAll().size()>=10){
                //懒的写了  搜索历史记录 满了之后  挤掉最后一个
                sharedPreferencesHelper.put("SR10",sharedPreferencesHelper.getSharedPreference("SR9","--"));
                sharedPreferencesHelper.put("SR9",sharedPreferencesHelper.getSharedPreference("SR8","--"));
                sharedPreferencesHelper.put("SR8",sharedPreferencesHelper.getSharedPreference("SR7","--"));
                sharedPreferencesHelper.put("SR7",sharedPreferencesHelper.getSharedPreference("SR6","--"));
                sharedPreferencesHelper.put("SR6",sharedPreferencesHelper.getSharedPreference("SR5","--"));
                sharedPreferencesHelper.put("SR5",sharedPreferencesHelper.getSharedPreference("SR4","--"));
                sharedPreferencesHelper.put("SR4",sharedPreferencesHelper.getSharedPreference("SR3","--"));
                sharedPreferencesHelper.put("SR3",sharedPreferencesHelper.getSharedPreference("SR2","--"));
                sharedPreferencesHelper.put("SR2",sharedPreferencesHelper.getSharedPreference("SR1","--"));
                sharedPreferencesHelper.put("SR1",EditString);
            }else{
                sharedPreferencesHelper.put("SR"+sharedPreferencesHelper.getAll().size(),EditString);
            }
            SEARCH( mvpView.getHomeSearchEdit_Edit().getText().toString().trim());
        }
    }
}
