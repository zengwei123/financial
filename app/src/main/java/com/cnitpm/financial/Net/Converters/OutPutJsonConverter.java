package com.cnitpm.financial.Net.Converters;



import com.cnitpm.financial.Model.OutPutJson;
import com.cnitpm.financial.Util.ZwGson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zengwei on 2018/11/12.
 */

public class OutPutJsonConverter<T> implements Converter<ResponseBody, OutPutJson<T>> {
    public static final OutPutJsonConverter INSTANCE = new OutPutJsonConverter();
    @Override
    public OutPutJson<T> convert(ResponseBody value) throws IOException {
        return ZwGson.GsonToBean(value.string(), OutPutJson.class);
    }
}
