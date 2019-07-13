package com.cnitpm.financial.Net;



import com.cnitpm.financial.Net.ConvertersFractory.OutPutJsonConverterFactory;
import com.cnitpm.financial.Net.ConvertersFractory.StringConverterFactory;
import com.cnitpm.financial.Net.Interceptor.AddQueryParameterInterceptor;
import com.cnitpm.financial.Util.Utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zengwei on 2018/11/12.
 * 请求的鸡鸡
 */

public class RetrofitCallOssService{
    private static RetrofitCallOssService retrofitCallOssService=null;
    public static RetrofitCallOssService getRetrofitCallOssService(){
        if (retrofitCallOssService==null){
            retrofitCallOssService=new RetrofitCallOssService();
        }
        return retrofitCallOssService;
    }
    public RetrofitRequestOssService getRetrofitRequestOssService(){
        retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
                .baseUrl(Utils.IP)  //192.168.43.91  192.168.31.74
                .addConverterFactory(OutPutJsonConverterFactory.create())   //自定义的bean解析器
                .addConverterFactory(StringConverterFactory.create())       //字符串解析器
                .addConverterFactory(GsonConverterFactory.create())        //gson解析器
                .client(getOkHttpClient())
                .build();
        RetrofitRequestOssService retrofitRequestOssService=retrofit.create(RetrofitRequestOssService.class);
        return retrofitRequestOssService;
    }
    private OkHttpClient getOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//默认重试一次，若需要重试N次，则要实现拦截器。
                .addInterceptor(new Retry(5))
                .addInterceptor(new AddQueryParameterInterceptor()) //拦截器  添加请求头
                .connectTimeout(10, TimeUnit.SECONDS)   //连接超时时间
                .readTimeout(20, TimeUnit.SECONDS)   //读取超时时间
                .writeTimeout(20, TimeUnit.SECONDS)  //写超时时间
                .build();
        return client;
    }
}
