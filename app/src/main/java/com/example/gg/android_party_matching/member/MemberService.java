package com.example.gg.android_party_matching.member;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MemberService {

    @FormUrlEncoded
    @POST("member/")
    Call<String> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("member/join")
    Call<String> join(@Field("email") String email, @Field("password") String password, @Field("name") String name);

    @GET("member/email_check")
    Call<String> email_check(@Query("email") String email);

    @FormUrlEncoded
    @POST("member/verify")
    Call<String> verify(@Field("token") String token);
}
