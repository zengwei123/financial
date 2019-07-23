package com.cnitpm.financial.Page.Fragment.TaoKe;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.view.View;

import com.cnitpm.financial.Base.BaseActivity;
import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.Material;
import com.cnitpm.financial.Model.OutPutJson;
import com.cnitpm.financial.Net.RetrofitCallOssService;
import com.cnitpm.financial.Page.Activity.Favorites.FavoritesActivity;
import com.cnitpm.financial.Page.Activity.HomeSearchEdit.HomeSearchEditActivity;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.Utils;

import java.util.ArrayList;

import okhttp3.internal.Version;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaoKePresenter extends BasePresenter<TaoKeView> implements View.OnClickListener {
    @Override
    public void init() {
        setView();
        a();
    }
    /**初始化**/
    private void setView(){
        mvpView.getHome_SearchEdit().setTypeface(Utils.getTypeFace(mvpView.getActivityContext()));
        mvpView.getHome_SearchEdit().setHint("\uecb3 输入关键字或商品标题");
        mvpView.getHome_SearchEdit().setInputType(InputType.TYPE_NULL);
        //侧拉
        mvpView.getHome_AppBar().addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {
                    //展开状态
                    mvpView.getHome_FloatingActionButton().setVisibility(View.GONE);
                }else if(state == State.COLLAPSED){
                    //折叠状态
                    mvpView.getHome_FloatingActionButton().setVisibility(View.VISIBLE);
                }else {
                    //中间状态
                }

            }
        });
        mvpView.getHome_FloatingActionButton().setOnClickListener(this);
        mvpView.getHome_Nine().setOnClickListener(this);
        mvpView.getHome_Big().setOnClickListener(this);
        mvpView.getHome_Good().setOnClickListener(this);
        mvpView.getHome_SearchEdit().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundles;
        switch (v.getId()){
            case R.id.Home_FloatingActionButton:
                mvpView.getHome_AppBar().setExpanded(true);
                // 这个是滑动到最下面 然后显示回到顶部按钮
                mvpView.getHome_Recycler().scrollToPosition(0);
                break;
            case R.id.Home_Nine:
                bundles=new Bundle();
                bundles.putString("value","9.9包邮");
                bundles.putInt("index",1);
                ((BaseActivity)mvpView.getActivityContext()).JumpBundleActivity(mvpView.getActivityContext(),FavoritesActivity.class,bundles);
            break;
            case R.id.Home_Big:
                bundles=new Bundle();
                bundles.putString("value","大额券");
                bundles.putInt("index",0);
                ((BaseActivity)mvpView.getActivityContext()).JumpBundleActivity(mvpView.getActivityContext(),FavoritesActivity.class,bundles);
                break;
            case R.id.Home_Good:
                bundles=new Bundle();
                bundles.putString("value","精品推荐");
                bundles.putInt("index",0);
                ((BaseActivity)mvpView.getActivityContext()).JumpBundleActivity(mvpView.getActivityContext(),FavoritesActivity.class,bundles);
                break;
            case R.id.Home_Article:

                break;
            case R.id.Home_SearchEdit:
                ((BaseActivity)mvpView.getActivityContext()).JumpActivity(mvpView.getActivityContext(), HomeSearchEditActivity.class);
                break;
        }
    }

    /**加载为你精选**/
    private void  a(){
        try {
            final Call<OutPutJson<ArrayList<Material>>> material= RetrofitCallOssService.getRetrofitCallOssService()
                    .getRetrofitRequestOssService()
                    .Favorites(0,null);
            material.enqueue(new Callback<OutPutJson<ArrayList<Material>>>() {
                @Override
                public void onResponse(Call<OutPutJson<ArrayList<Material>>> call, Response<OutPutJson<ArrayList<Material>>> response) {
                    try {
                        //加载成功
                        mvpView.setHome_Adapter(mvpView.getActivityContext(),response.body().getDate());
                        mvpView.getHome_Recycler().setLayoutManager(new LinearLayoutManager(mvpView.getActivityContext()));
                        mvpView.getHome_Recycler().setAdapter(mvpView.getHome_Adapter());
                    }catch (Exception e){
                    }
                }

                @Override
                public void onFailure(Call<OutPutJson<ArrayList<Material>>> call, Throwable t) {
                    try {
                        //失败就再来一次  无限来
                        a();
                    }catch (Exception e){
                    }
                }
            });
        }catch (Exception e){

        }
    }
}
