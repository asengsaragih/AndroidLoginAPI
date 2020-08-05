package com.suncode.loginapi.helper;

import com.suncode.loginapi.constant.Base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseGenerator {

    public static Retrofit build() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Base.SUNCODE_BASE)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
