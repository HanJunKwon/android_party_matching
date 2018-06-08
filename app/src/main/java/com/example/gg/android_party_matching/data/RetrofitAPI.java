package com.example.gg.android_party_matching.data;

import com.example.gg.android_party_matching.member.MemberService;
import com.example.gg.android_party_matching.party.PartyService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {

    private static RetrofitAPI ourInstance = new RetrofitAPI();
    public static RetrofitAPI getInstance(){
        return ourInstance;
    }
    private RetrofitAPI(){}

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.keide.site/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    // 각 기능 추가 할 때마다 등록
    MemberService memberService = retrofit.create(MemberService.class);
    PartyService partyService = retrofit.create(PartyService.class);

    public MemberService getMemberService(){return memberService;}
    public PartyService getPartyService(){return partyService;}

}
