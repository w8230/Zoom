package com.android.zoom.Common.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    /**
     * base url : 각자 개인 서버+포트
     * Retrofit 은 주로 싱글톤으로 사용하기 때문에,
     * instance 전역 변수를 선언하고, static 을 줌으로써
     * 인스턴스화 하지 않고 사용할 수 있게 해야한다.
     * */

    private static RetrofitBuilder instance = null;
    private static RetrofitService service;
    private String baseUrl = "http://222.100.239.140:8080/";

    public RetrofitBuilder(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);
    }

    public static RetrofitBuilder getInstance(String url) {
        if (instance == null) {
            instance = new RetrofitBuilder(url);
        }
        return instance;
    }

    public static RetrofitService getRetrofitService() {
        return service;
    }
}
