package com.example.gg.android_party_matching.Util;

public interface StaticUtil {
    // 서버 URL 주소
    public final static String server_base_url ="112.172.20.174:8080/server";
    public final static String sign_up_url = "/member/join";
    public final static String email_check_url ="/member/email_check";
    public final static String login_url ="/member";
    public final static String session_url = "/member/verify";
    public final static String user_email ="email";
    public final static String user_password = "password";
    public final static String token = "token";
    public final static String isAutoLogin = "isAutoLogin";

    public final static String dialog_positive = "예";
    public final static String dialog_negative ="아니요";

    public final static String SignUp = "입력한 정보로 회원가입하시겠습니까?";
    public final static String SignUpSuccess = "회원가입을 완료하였습니다!";
}
