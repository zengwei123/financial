package com.cnitpm.financial.Page.Activity.Favorites;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.Material;
import com.cnitpm.financial.Model.OutPutJson;
import com.cnitpm.financial.Net.RetrofitCallOssService;
import com.cnitpm.financial.Page.Fragment.TaoKe.Home_Adapter;
import com.cnitpm.financial.Util.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zengwei on 2019/2/2.
 */

public class FavoritesPresenter extends BasePresenter<FavoritesView> {

    @Override
    public void init() {
        mvpView.getTitle_Back().setTypeface(Utils.getTypeFace(mvpView.getActivityContext()));
        mvpView.getTitle_Back().setText("\ue907");
        mvpView.getTitle_Back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpView.getThisActivity().finish();
            }
        });
        mvpView.getTitle_Message().setText(mvpView.getBundle().getString("value"));
        a();
    }
    private void a(){
        final Call<OutPutJson<ArrayList<Material>>> material= RetrofitCallOssService.getRetrofitCallOssService()
                .getRetrofitRequestOssService()  //AdzoneId 用户的pid  用来获取返利购买链接的
                .Favorites(mvpView.getBundle().getInt("index"),null);
        material.enqueue(new Callback<OutPutJson<ArrayList<Material>>>() {
            @Override
            public void onResponse(Call<OutPutJson<ArrayList<Material>>> call, Response<OutPutJson<ArrayList<Material>>> response) {
                try {
                    //加载成功
                    mvpView.setHome_Adapter(mvpView.getActivityContext(),response.body().getDate());
                    mvpView.getFavorites_Recycler().setLayoutManager(new LinearLayoutManager(mvpView.getActivityContext()));
                    mvpView.getFavorites_Recycler().setAdapter(mvpView.getHome_Adapter());
                }catch (Exception e){
                }
            }

            @Override
            public void onFailure(Call<OutPutJson<ArrayList<Material>>> call, Throwable t) {
                try {
                    Toast.makeText(mvpView.getActivityContext(), "网络异常", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                }
            }
        });
    }
}
