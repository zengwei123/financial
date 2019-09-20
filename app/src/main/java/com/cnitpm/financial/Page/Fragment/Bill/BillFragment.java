package com.cnitpm.financial.Page.Fragment.Bill;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Base.MvpFragment;
import com.cnitpm.financial.Base.ViewBind;
import com.cnitpm.financial.R;

import retrofit2.http.GET;

public class BillFragment extends MvpFragment<BillPresenter> implements BillView{
    @ViewBind(R.id.Bill_Add_Layout)
    private RelativeLayout Bill_Add_Layout;
    @ViewBind(R.id.Bill_Add_Button)
    private Button Bill_Add_Button;
    @ViewBind(R.id.Bill_Initial_Text)
    private TextView Bill_Initial_Text;
    @ViewBind(R.id.Bill_End_Text)
    private TextView Bill_End_Text;
    @ViewBind(R.id.Bill_Generate)
    private TextView Bill_Generate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bill_fragment,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter.attachView(this);
        injectViews(view);
        mvpPresenter.init();
    }

    @Override
    public Context getActivityContext() {
        return activity;
    }

    @Override
    public Activity getThisActivity() {
        return activity;
    }

    @Override
    protected BillPresenter createPresenter() {
        return new BillPresenter();
    }

    @Override
    public RelativeLayout getBill_Add_Layout() {
        return Bill_Add_Layout;
    }

    @Override
    public Button getBill_Add_Button() {
        return Bill_Add_Button;
    }

    @Override
    public TextView getBill_Initial_Text() {
        return Bill_Initial_Text;
    }

    @Override
    public TextView getBill_End_Text() {
        return Bill_End_Text;
    }

    @Override
    public TextView getBill_Generate() {
        return Bill_Generate;
    }

    @Override
    public void onStart() {
        super.onStart();
        mvpPresenter.init();
    }

}
