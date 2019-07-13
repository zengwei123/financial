package com.cnitpm.financial.Net.Converters;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zengwei on 2018/11/12.
 */

public class StringConverter implements Converter<ResponseBody, String> {
    public static final StringConverter INSTANCE = new StringConverter();
    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}

