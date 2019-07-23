package com.cnitpm.financial.Page.Activity.SearchList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnitpm.financial.Model.Material;
import com.cnitpm.financial.R;
import com.cnitpm.financial.Util.Utils;

import java.util.List;


/**
 * Created by zengwei on 2019/1/24.
 */

public class SearchListRecycler_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
//    private GlideImageLoader glideImageLoader;
//    private Context context;
//    private List<Material> materials;
//    // 普通布局
//    private final int TYPE_ITEM = 1;
//    // 脚布局
//    private final int TYPE_FOOTER = 2;
//
//    public SearchListRecycler_Adapter(Context context, List<Material> materials){
//        this.context=context;
//        this.materials=materials;glideImageLoader=new GlideImageLoader();
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == TYPE_ITEM) {
//            return new SearchListRecycler_Adapter_ViewHolder(LayoutInflater.from(context).inflate(R.layout.searchlist_recycler,parent,false));
//        }else {
//            return new SearchListRecycler_Adapter_ViewHolder1(LayoutInflater.from(context).inflate(R.layout.searchlist_recycler_load,parent,false));
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
//        if(holder instanceof SearchListRecycler_Adapter_ViewHolder){
//            SearchListRecycler_Adapter_ViewHolder holder_1= (SearchListRecycler_Adapter_ViewHolder) holder;
//            glideImageLoader.displayImage(context,materials.get(position).getPict_url(),holder_1.SearchList_Recycler_Img);
//            holder_1.SearchList_Recycler_title.setText(materials.get(position).getTitle());
//
//            double dz=0;
//            if(materials.get(position).getCoupon_info()!=null){
//                try {
//                    dz= Double.parseDouble(materials.get(position).getZk_final_price())- Double.parseDouble(Utils.getQuan(materials.get(position).getCoupon_info(),false));
//                    holder_1.SearchList_Recycler_zk_final_price.setText("￥"+Utils.getCommission_rate((Double.parseDouble(materials.get(position).getZk_final_price())- Double.parseDouble(Utils.getQuan(materials.get(position).getCoupon_info(),false)))));
//                }catch (Exception e){
//                    dz= Double.parseDouble(materials.get(position).getZk_final_price());
//                    holder_1.SearchList_Recycler_zk_final_price.setText("￥"+materials.get(position).getZk_final_price());
//                }
//
//            }else {
//                dz= Double.parseDouble(materials.get(position).getZk_final_price());
//                holder_1.SearchList_Recycler_zk_final_price.setText("￥"+materials.get(position).getZk_final_price());
//            }
//
//
//            holder_1.SearchList_Recycler_tk_total_sales.setText("已售"+materials.get(position).getTk_total_sales());
//            holder_1.SearchList_Recycler_commission_rate.setText("预计佣金："+Utils.getCommission_rate(dz* Double.parseDouble(materials.get(position).getCommission_rate())/100/100/2)+"元");
//            holder_1.SearchList_Recycler_coupon_info.setText("券:"+materials.get(position).getCoupon_info());
//            holder_1.SearchList_Recycler_Layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(context, SearchListDetailsActivity.class);
//                    Bundle bundle=new Bundle();
//                    bundle.putSerializable("value",materials.get(position));
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
//                }
//            });
//            if(materials.get(position).getUser_type()!=0){
//                mode8(R.drawable.tianmao,holder_1.SearchList_Recycler_title);
//            }else {
//                mode8(R.drawable.taobao,holder_1.SearchList_Recycler_title);
//            }
//        }
//    }
//    private void mode8(int iz, TextView textView) {
//        SpannableStringBuilder spannableString = new SpannableStringBuilder();
//        spannableString.append("- "+textView.getText());
//        ImageSpan imageSpan = new ImageSpan(context, iz);
//        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//        textView.setText(spannableString);
//    }
//    @Override
//    public int getItemCount() {
//        return materials.size()+1;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        // 最后一个item设置为FooterView
//        if (position + 1 == getItemCount()) {
//            return TYPE_FOOTER;
//        } else {
//            return TYPE_ITEM;
//        }
//    }
//    class SearchListRecycler_Adapter_ViewHolder extends RecyclerView.ViewHolder{
//        ImageView SearchList_Recycler_Img;
//        TextView SearchList_Recycler_title,SearchList_Recycler_zk_final_price,
//                SearchList_Recycler_tk_total_sales,
//                SearchList_Recycler_commission_rate,SearchList_Recycler_coupon_info;
//        LinearLayout SearchList_Recycler_Layout;
//        public SearchListRecycler_Adapter_ViewHolder(View itemView) {
//            super(itemView);
//            SearchList_Recycler_Layout=itemView.findViewById(R.id.SearchList_Recycler_Layout);
//            SearchList_Recycler_Img=itemView.findViewById(R.id.SearchList_Recycler_Img);
//            SearchList_Recycler_title=itemView.findViewById(R.id.SearchList_Recycler_title);
//            SearchList_Recycler_zk_final_price=itemView.findViewById(R.id.SearchList_Recycler_zk_final_price);
//            SearchList_Recycler_tk_total_sales=itemView.findViewById(R.id.SearchList_Recycler_tk_total_sales);
//            SearchList_Recycler_commission_rate=itemView.findViewById(R.id.SearchList_Recycler_commission_rate);
//            SearchList_Recycler_coupon_info=itemView.findViewById(R.id.SearchList_Recycler_coupon_info);
//        }
//    }
//    class SearchListRecycler_Adapter_ViewHolder1 extends RecyclerView.ViewHolder{
//        public LottieAnimationView lottieAnimationView;
//        public TextView yijindaodi;
//        public SearchListRecycler_Adapter_ViewHolder1(View itemView) {
//            super(itemView);
//            lottieAnimationView=itemView.findViewById(R.id.animation_view);
//            yijindaodi=itemView.findViewById(R.id.yijindaodi);
//            lottieAnimationView.setVisibility(View.GONE);
//        }
//    }
}
