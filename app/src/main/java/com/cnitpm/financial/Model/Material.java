package com.cnitpm.financial.Model;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zengwei on 2019/1/24.
 * 物料商品
 */

public class Material extends LitePalSupport implements Serializable {
    private String tk_total_sales;  //推广量
    private String num_iid;  //宝贝Id
    private String title;  //商品标题
    private String pict_url;  //商品主图
    private TK small_images;  //商品小图列表
    private String reserve_price; //一口价格
    private String zk_final_price;  //折扣价格
    private String item_url;  //商品地址
    private int user_type;
    private String commission_rate; //佣金比例
    private String coupon_info;   //优惠券面额
    private String coupon_share_url;//券二合一页面链接
    private String url;  //淘客地址
    private String short_title;
    public String getTk_total_sales() {
        return tk_total_sales;
    }

    public void setTk_total_sales(String tk_total_sales) {
        this.tk_total_sales = tk_total_sales;
    }

    public String getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPict_url() {
        return pict_url;
    }

    public void setPict_url(String pict_url) {
        this.pict_url = pict_url;
    }

    public TK getSmall_images() {
        return small_images;
    }

    public void setSmall_images(TK small_images) {
        this.small_images = small_images;
    }

    public String getReserve_price() {
        return reserve_price;
    }

    public void setReserve_price(String reserve_price) {
        this.reserve_price = reserve_price;
    }

    public String getZk_final_price() {
        return zk_final_price;
    }

    public void setZk_final_price(String zk_final_price) {
        this.zk_final_price = zk_final_price;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public String getCommission_rate() {
        return commission_rate;
    }

    public String getCoupon_info() {
        return coupon_info;
    }

    public void setCoupon_info(String coupon_info) {
        this.coupon_info = coupon_info;
    }

    public void setCommission_rate(String commission_rate) {
        this.commission_rate = commission_rate;
    }

    public String getCoupon_share_url() {
        return coupon_share_url;
    }

    public void setCoupon_share_url(String coupon_share_url) {
        this.coupon_share_url = coupon_share_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    @Override
    public String toString() {
        return "Material{" +
                "tk_total_sales='" + tk_total_sales + '\'' +
                ", num_iid='" + num_iid + '\'' +
                ", title='" + title + '\'' +
                ", pict_url='" + pict_url + '\'' +
                ", small_images=" + small_images +
                ", reserve_price='" + reserve_price + '\'' +
                ", zk_final_price='" + zk_final_price + '\'' +
                ", item_url='" + item_url + '\'' +
                ", commission_rate='" + commission_rate + '\'' +
                ", coupon_info='" + coupon_info + '\'' +
                ", coupon_share_url='" + coupon_share_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public static class TK implements Serializable {
        private List<String> string;
        public List<String> getString() {
            return string;
        }
        public void setString(List<String> string) {
            this.string = string;
        }
        @Override
        public String toString() {
            return "TK{" +
                    "string=" + string +
                    '}';
        }
    }
}
