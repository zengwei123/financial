package com.cnitpm.financial.Net.Interceptor;



import com.cnitpm.financial.Base.BaseApplication;
import com.cnitpm.financial.Util.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zengwei on 2018/11/13.
 * 拦截器 用来添加请求头等东西
 */

public class AddQueryParameterInterceptor implements Interceptor{
    private Request.Builder builder;
    private Request originalRequest;
    private Request.Builder requestBuilder;
    private Request request;
    @Override
    public Response intercept(Chain chain) throws IOException {
        originalRequest = chain.request();
        builder = originalRequest.newBuilder();
        requestBuilder =builder.method(originalRequest.method(), originalRequest.body());
        request = requestBuilder.build();
        return chain.proceed(request);
    }

}
