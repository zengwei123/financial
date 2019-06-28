package com.cnitpm.financial.Page.Activity.Search;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Custom.WanEditText;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.TimeLine;
import com.cnitpm.financial.Util.SqlOperation;

import java.util.ArrayList;
import java.util.List;

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
        mvpView.getSearch_EditText_wan().setFocusable(true);
        mvpView.getSearch_EditText_wan().setFocusableInTouchMode(true);
        mvpView.getSearch_EditText_wan().requestFocus();
        mvpView.getThisActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        mvpView.getSearch_Recycler().setLayoutManager(new LinearLayoutManager(mvpView.getActivityContext()));
        mvpView.getSearch_EditText_wan().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)  {
                if ((event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                    String str=mvpView.getSearch_EditText_wan().getText().toString().trim();
                    if(str.equals("")){
                        Toast.makeText(mvpView.getActivityContext(), "查询内容不能为空！", Toast.LENGTH_SHORT).show();
                    }else {
                        List<AllModel> allModels=SearchData(str);
                        mvpView.getSearch_Recycler().setAdapter(new SearchRecyclerAdapter(mvpView.getActivityContext(),TimeLine.class,allModels));
                        ((InputMethodManager) mvpView.getThisActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(mvpView.getThisActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return false;
            }
        });
        mvpView.getSearch_EditText_wan().setRightPicOnclickListener(new WanEditText.RightPicOnclickListener() {
            @Override
            public void rightPicClick(EditText editText) {
                editText.setText("");
            }
        });
    }

    private List<AllModel> SearchData(String msg){
        List<AllModel> allModels=new ArrayList<>();
        List<TimeLine> timeLines=new SqlOperation().SelectWhere(TimeLine.class,"Time like ? or Message like ? group by id","%"+msg+"%","%"+msg+"%");
        for (TimeLine timeLine:timeLines){
            allModels.add(new AllModel(timeLine,1));
        }
        return allModels;
    }

}
