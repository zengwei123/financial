package com.cnitpm.financial.Page.Activity.AddRecord.ViewPage;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.AllModel;
import com.cnitpm.financial.Model.CIModel;
import com.cnitpm.financial.Util.UtilRecyclerAdapter;
import com.cnitpm.financial.Util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ARViewPagePresenter extends BasePresenter<ARViewPageView> {
    @Override
    public void init() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mvpView.getActivityContext(), 5);
        //设置表格，根据position计算在该position处1列占几格数据
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return 5;
            }
        });
        mvpView.getARViewPager_Recycler_ClassChoose().setLayoutManager(gridLayoutManager);
        if (mvpView.getIsRL()){
            mvpView.getARViewPager_Recycler_ClassChoose().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),CIModel.class,getAllModel(Utils.RightIcon,Utils.RightClass)));
        }else {
            mvpView.getARViewPager_Recycler_ClassChoose().setAdapter(new UtilRecyclerAdapter(mvpView.getActivityContext(),CIModel.class,getAllModel(Utils.LeftIcon,Utils.LeftClass)));
        }
        ((UtilRecyclerAdapter)mvpView.getARViewPager_Recycler_ClassChoose().getAdapter()).setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mvpView.getARViewPageInterface().ItemClick(position);   //调用回调接口
            }
        });
    }
    /**获取相应的类型数据**/
    private List<AllModel> getAllModel(int[] icon,String[] aclass){
        List<AllModel> allModels=new ArrayList<>();
        for(int i=0;i<aclass.length;i++){
            allModels.add(new AllModel(new CIModel(icon[i],aclass[i]),1));
        }
        return allModels;
    }

}
