package com.example.gg.android_party_matching;


import com.example.gg.android_party_matching.Util.StaticUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

// 싱글톤
public class NetRetrofit implements StaticUtil {
    private static NetRetrofit ourInstance = new NetRetrofit();
    public static NetRetrofit getOurInstance(){
        return ourInstance;
    }

    private NetRetrofit(){ }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(StaticUtil.server_base_url) //
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()) // 파싱을 위해서 컨버터 추가
            // .client(client)
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class); // API통시늘 위해 정의한 RetroService를 Retrofit에 초기화 해준다.

    public RetrofitService getService() {
        return service;
    }
}
