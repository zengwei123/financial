package com.cnitpm.financial.Net;




import com.cnitpm.financial.Model.Material;
import com.cnitpm.financial.Model.OutPutJson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import okhttp3.internal.Version;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by zengwei on 2018/11/12.
 * 接口
 */

public interface RetrofitRequestOssService {
    @FormUrlEncoded
    @POST("Guest/getMaterial")   //物料
    Call<OutPutJson<ArrayList<Material>>> Material(@Field("AdzoneId") Long AdzoneId,
                                                   @Field("q") String q,
                                                   @Field("sort") String sort,
                                                   @Field("has_coupon") boolean has_coupon,
                                                   @Field("page_no") long page_no);
    @FormUrlEncoded
    @POST("Guest/getTpwd")   //物料
    Call<OutPutJson<String>> Tpwd(@Field("Url") String Url, @Field("Url1") String Url1);

    @FormUrlEncoded
    @POST("Guest/getFavorites")   //9.9  大额券
    Call<OutPutJson<ArrayList<Material>>> Favorites(@Field("i") int i, @Field("AdzoneId") Long AdzoneId);

}
