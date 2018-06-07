package com.example.gg.android_party_matching;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

// 웹서버와 통신을 위한 API를 정의하기 위한 interface
// 참고 사이트 :http://falinrush.tistory.com/5
// 참고 사이트 :http://march3samwuli.tistory.com/entry/Retrofit-2%EB%A1%9C-Http%ED%86%B5%EC%8B%A0-%EA%B5%AC%ED%98%84-%EC%8B%9C%EA%B0%84%EC%9D%84-%EB%8B%A8%EC%B6%95%ED%95%B4%EB%B3%B4%EC%9E%90
public interface RetrofitService {
    /*
     * Call이 뭐하는 클래스인지는 알 필요가 없다.
     * 중요한 건 이 api의 응답을 <> 안에 지정된 객체가 데이터를 받는다는 것이다.
     * 서버에서 응답받은 JSON에서 email과 password 속성을 떼어서 저장
     */
    @Headers({"application_join"})
    @POST("member/join")
    Call<Member> member_join(@Query("email") String email, @Query("password") String password);
}
