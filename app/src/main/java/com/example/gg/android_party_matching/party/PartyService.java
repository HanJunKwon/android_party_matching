package com.example.gg.android_party_matching.party;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PartyService {

    // 파티 만들기
    @FormUrlEncoded
    @POST("party/make")
    Call<String> make(@Field("classification") int classification, @Field("jwt") String jwt,
                      @Field("title") String title, @Field("contents") String contents, @Field("max_people") String max_people,
                      @Field("party_date") String party_date, @Field("latitude") String latitude, @Field("longitude") String longitude);

    // 파티 리스트
    @GET("party/list")
    Call<List<PartyVO>> list (@Query("classification") int classification, @Query("latitude") String latitude, @Query("longitude") String longitude, @Query("range") int range);

    // 파티 참여
    @FormUrlEncoded
    @POST("party/join")
    Call<String> join (@Field("jwt") String jwt, @Field("party_code") long party_code);
}
