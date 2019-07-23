package com.cnitpm.financial.Page.Activity.SearchList;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;

import com.cnitpm.financial.Base.BasePresenter;
import com.cnitpm.financial.Model.Material;
import com.cnitpm.financial.Model.OutPutJson;
import com.cnitpm.financial.Net.RetrofitCallOssService;
import com.cnitpm.financial.Page.Activity.HomeSearchEdit.HomeSearchEditActivity;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.SharedPreferencesHelper;
import com.cnitpm.financial.Util.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by zengwei on 2019/1/23.
 */

public class SearchListPresenter extends BasePresenter<SearchListView> implements View.OnClickListener{
    private boolean isInit=true;  //是否初次加载数据
    private List<Material> Materials;
    private boolean Z=true;
    private boolean Z1=true;
    private boolean Z2=false;  //是否显示优惠券
    private String sort;   //排序的方法
    private long page_noZ=1;  //页数
    private Dialog dialog;
    private View views;   //上拉加载的布局
  //  private SearchListRecycler_Adapter.SearchListRecycler_Adapter_ViewHolder1 searchListRecycler_adapter_viewHolder1;
    private int networksum=0;
    @Override
    public void init() {

//       // dialog.show();
//        setSearchList_Back();
//        setSearchList_Edit();
////        a(null,null,false,page_noZ);
//        setSort();
    }

//加载数据的方法
//    private void a(Long AdzoneId, final String sort, boolean has_coupon, long page_no){
//        try {
//            final Call<OutPutJson<ArrayList<Material>>> material= RetrofitCallOssService.getRetrofitCallOssService().getRetrofitRequestOssService().Material(AdzoneId,mvpView.getSearchList_Edit().getText().toString().trim(),sort,has_coupon,page_no);
//            material.enqueue(new Callback<OutPutJson<ArrayList<Material>>>() {
//            @Override
//            public void onResponse(Call<OutPutJson<ArrayList<Material>>> call, Response<OutPutJson<ArrayList<Material>>> response) {
//                try {
//                    mvpView.getSearchList_Recycler().setVisibility(View.VISIBLE);  //显示列表
//                    mvpView.getSearchList_ExceptionLayout().setVisibility(View.GONE);   //隐藏数据的图片
//                    networksum=0;   //网络不好时候的加载次数归0
//                    //如果是第一次那么
//                    if(isInit){
//                        Materials=response.body().getDate(); //获取数据
//                        if(Materials==null){   //好  没有数据
//                            throw new Exception();  //主动抛出异常
//                        }
//                        //列表的初始化
//                        mvpView.setSearchListRecycler_Adapter(mvpView.getSearchListActivity(),Materials);
//                        mvpView.getSearchList_Recycler().setAdapter(mvpView.getSearchListRecycler_Adapter());
//                        mvpView.getSearchList_Recycler().setLayoutManager(new LinearLayoutManager(mvpView.getSearchListActivity()));
//                        isInit=false;   //不是第一次了  不需要初始化这些东西了
//                        //上拉加载
//                        mvpView.getSearchList_Recycler().addOnScrollListener(new RecyclerView.OnScrollListener() {
//                            @Override
//                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                                super.onScrollStateChanged(recyclerView, newState);
//                                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                                // 当不滚动时
//                                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                                    //获取最后一个完全显示的ItemPosition
//                                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//                                    int totalItemCount = manager.getItemCount();
//                                    try {
//                                        // 判断是否滚动到底部，并且是向右滚动
//                                        if (lastVisibleItem == (totalItemCount - 1) ) {
//                                            //加载更多功能的代码
//                                            views=manager.findViewByPosition(lastVisibleItem);
//                                            searchListRecycler_adapter_viewHolder1= (SearchListRecycler_Adapter.SearchListRecycler_Adapter_ViewHolder1) recyclerView.getChildViewHolder(views);
//                                            if(views!=null){
//                                                searchListRecycler_adapter_viewHolder1.lottieAnimationView.setVisibility(View.VISIBLE);
//                                                searchListRecycler_adapter_viewHolder1.lottieAnimationView.playAnimation();
//                                                page_noZ++;  //加载下一页的数据
//                                                //判断是否没有数据了
//                                                if(searchListRecycler_adapter_viewHolder1.yijindaodi.getVisibility()== View.VISIBLE){
//                                                    //没有数据了 那么没有数据提示就已经显示了  吧这个动画隐藏
//                                                    searchListRecycler_adapter_viewHolder1.lottieAnimationView.setVisibility(View.GONE);
//                                                }else{
//                                                    //还有数据  继续加载
//                                                    a(null,sort,Z2,page_noZ);
//                                                }
//                                            }
//                                        }
//                                    }catch (Exception e){
//                                        //应该没用吧？  管他呢 放这里
//                                    }
//                                }
//                            }
//                        });
//                    }else{
//                        //不是第一次  但是是重新加载第一页  如选择了价格排序
//                        if(page_noZ==1){
//                            Materials.clear(); //清除原来的数据
//                        }
//                        //添加数据
//                        for(Material m:response.body().getDate()){
//                            Materials.add(m);
//                        }
//                        //刷新item
//                        mvpView.getSearchListRecycler_Adapter().notifyDataSetChanged();
//                    }
//                }catch (Exception e){
//                    //进入这个那么就说明 没有数据了
//                    if(page_noZ==1) {   //才第一页就到这里了  那么说明这个关键词没有数据
//                        mvpView.getSearchList_Recycler().setVisibility(View.GONE);
//                        mvpView.getSearchList_ExceptionLayout().setVisibility(View.VISIBLE);
//                        mvpView.getSearchList_No().setImageResource(R.mipmap.nogoods);
//                    }else{
//                        //说明有数据  但是加载完了  显示已经到底
//                        searchListRecycler_adapter_viewHolder1.lottieAnimationView.setVisibility(View.GONE);
//                        searchListRecycler_adapter_viewHolder1.yijindaodi.setVisibility(View.VISIBLE);
//                    }
//                }
//                //关闭 拉拉
//                try {
//                    dialog.dismiss();
//                }catch (Exception e){
//                }
//            }
//
//            @Override
//            public void onFailure(Call<OutPutJson<ArrayList<Material>>> call, Throwable t) {
//                try {
//                    //没用加载出数据
//                    networksum++;  //记录次数
//                    if(networksum<=2){
//                        a(null,sort,Z2,page_noZ);  //加载
//                    }else {
//                        networksum=10;   //没用  用来做标记
//                        mvpView.getSearchList_Recycler().setVisibility(View.GONE);
//                        mvpView.getSearchList_ExceptionLayout().setVisibility(View.VISIBLE);
//                        mvpView.getSearchList_No().setImageResource(R.drawable.nonetwork);  //跟换显示图片
//                        dialog.dismiss();
//                    }
//                }catch (Exception e){
//                }
//            }
//        });
//        }catch (Exception E){
//        }
//    }

    //返回按钮
    private void setSearchList_Back(){
        mvpView.getSearchList_Back().setTypeface(Utils.getTypeFace(mvpView.getSearchListActivity()));
        mvpView.getSearchList_Back().setText("\ue922");
        mvpView.getSearchList_Back().setOnClickListener(this);
        mvpView.getSearchList_No().setOnClickListener(this);   //网络不好重新加载数据
    }
    //搜索
    private void setSearchList_Edit(){
        mvpView.getSearchList_Edit().setTypeface(Utils.getTypeFace(mvpView.getSearchListActivity()));
        mvpView.getSearchList_Edit().setHint("\ueafc 输入关键字或商品标题");
        mvpView.getSearchList_Edit().setText(mvpView.getBundle().getString("value"));
        mvpView.getSearchList_Edit().setInputType(InputType.TYPE_NULL);
        mvpView.getSearchList_Edit().setOnClickListener(this);
    }
    //排序
    private void setSort(){
        //优惠券textview
        mvpView.getSearchList_Checkbox_Ticket().setTypeface(Utils.getTypeFace(mvpView.getSearchListActivity()));
        mvpView.getSearchList_Checkbox_Ticket().setText("\uec22 仅显示优惠券商品");
        //综合按钮
        mvpView.getSearchList_sort1().setOnClickListener(this);
        mvpView.getSearchList_sort1().setTextColor(Color.parseColor("#DE0000"));
        //价格排序按钮
        mvpView.getSearchList_sort2().setOnClickListener(this);
        mvpView.getSearchList_sort2().setTypeface(Utils.getTypeFace(mvpView.getSearchListActivity()));
        mvpView.getSearchList_sort2().setText("价格\ue94c");
        //销售额排序按钮
        mvpView.getSearchList_sort3().setOnClickListener(this);
        mvpView.getSearchList_sort3().setTypeface(Utils.getTypeFace(mvpView.getSearchListActivity()));
        mvpView.getSearchList_sort3().setText("销量\ue94c");
        //是否显示优惠券按钮
        mvpView.getSearchList_Checkbox().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.SearchList_Back:
//                mvpView.getSearchListActivity().finish();
//                mvpView.getSearchListActivity().overridePendingTransition(R.anim.activity_out,R.anim.activity_into);
//                break;
//            case R.id.SearchList_Edit:
//                mvpView.getBundle().putString("value", mvpView.getSearchList_Edit().getText().toString().trim());
//                mvpView.getSearchListActivity().startActivity(HomeSearchEditActivity.class, mvpView.getBundle());
//                mvpView.getSearchListActivity().finish();
//                mvpView.getSearchListActivity().overridePendingTransition(R.anim.activity_out,R.anim.activity_into);
//                break;
//            case R.id.SearchList_sort1:
//                page_noZ=1;
//                mvpView.getSearchList_sort1().setTextColor(Color.parseColor("#DE0000"));
//                mvpView.getSearchList_sort2().setTextColor(Color.parseColor("#2b2b2b"));
//                mvpView.getSearchList_sort3().setTextColor(Color.parseColor("#2b2b2b"));
//                mvpView.getSearchList_sort2().setText("价格\ue94c");
//                mvpView.getSearchList_sort3().setText("价格\ue94c");
//                if(user!=null){
//                    a(Utils.Pid(user.getPid().getPid()),null,Z2,1);
//                }else{
//                    a(null,null,Z2,1);
//                }
//                Z=true;
//                Z1=true;
//                dialog.show();
//                sort=null;
//                break;
//            case R.id.SearchList_sort2:
//                page_noZ=1;
//                mvpView.getSearchList_sort2().setTextColor(Color.parseColor("#DE0000"));
//                mvpView.getSearchList_sort1().setTextColor(Color.parseColor("#2b2b2b"));
//                mvpView.getSearchList_sort3().setTextColor(Color.parseColor("#2b2b2b"));
//                if(Z){
//                    mvpView.getSearchList_sort2().setText("价格\ue950");
//                    if(user!=null){
//                        a(Utils.Pid(user.getPid().getPid()),"price_asc",Z2,1);
//                    }else{
//                        a(null,"price_asc",Z2,1);
//                    }
//                    sort="price_asc";
//                    Z=false;
//                }else{
//                    mvpView.getSearchList_sort2().setText("价格\ue953");
//                    if(user!=null){
//                        a(Utils.Pid(user.getPid().getPid()),"price_des",Z2,1);
//                    }else{
//                        a(null,"price_des",Z2,1);
//                    }
//                    sort="price_des";
//                    Z=true;
//                }
//                Z1=true;
//                dialog.show();
//                LoadingDialogView.lottieAnimationView1.playAnimation();
//                break;
//            case R.id.SearchList_sort3:
//                page_noZ=1;
//                mvpView.getSearchList_sort3().setTextColor(Color.parseColor("#DE0000"));
//                mvpView.getSearchList_sort1().setTextColor(Color.parseColor("#2b2b2b"));
//                mvpView.getSearchList_sort2().setTextColor(Color.parseColor("#2b2b2b"));
//                if(Z1){
//                    mvpView.getSearchList_sort3().setText("销量\ue950");
//                    if(user!=null){
//                        a(Utils.Pid(user.getPid().getPid()),"total_sales_asc",Z2,1);
//                    }else{
//                        a(null,"total_sales_asc",Z2,1);
//                    }
//                    sort="total_sales_asc";
//                    Z1=false;
//                }else{
//                    mvpView.getSearchList_sort3().setText("销量\ue953");
//                    if(user!=null){
//                        a(Utils.Pid(user.getPid().getPid()),"total_sales_des",Z2,1);
//                    }else{
//                        a(null,"total_sales_des",Z2,1);
//                    }
//                    sort="total_sales_des";
//                    Z1=true;
//                }
//                dialog.show();
//                LoadingDialogView.lottieAnimationView1.playAnimation();
//                break;
//            case R.id.Searchlist_Checkbox:
//                page_noZ=1;
//                if(Z2){
//                    if(user!=null){
//                        a(Utils.Pid(user.getPid().getPid()),sort,false,1);
//                    }else{
//                        a(null,sort,false,1);
//                    }
//                    Z2=false;
//                }else{
//                    if(user!=null){
//                        a(Utils.Pid(user.getPid().getPid()),sort,true,1);
//                    }else{
//                        a(null,sort,true,1);
//                    }
//                    Z2=true;
//                }
//                dialog.show();
//                LoadingDialogView.lottieAnimationView1.playAnimation();
//                break;
//            case R.id.SearchList_No:
//                if(networksum==10){
//                    if(user!=null){
//                        a(Utils.Pid(user.getPid().getPid()),sort,Z2,page_noZ);
//                    }else{
//                        a(null,sort,Z2,page_noZ);
//                    }
//                    dialog.show();
//                    LoadingDialogView.lottieAnimationView1.playAnimation();
//                    networksum=0;
//                }
//                break;
//        }
//    }
}
