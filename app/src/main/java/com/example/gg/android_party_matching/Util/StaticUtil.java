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

    public final static String signUp = "입력한 정보로 회원가입하시겠습니까?";
    public final static String signUpSuccess = "회원가입을 완료하였습니다!";

    public final static String server_connection_error = "서버와 연결을 실패하였습니다";
    public final static String user_widthdraw ="탈퇴 완료";
    public final static String check_email_or_password="이메일이나 비밀번호를 확인해주세요.";
    public final static String email_overlap = "중복되는 이메일이 존재합니다";
    public final static String loading = "서버에 접속 중입니다.";

    public final static String meal = "식사";
    public final static String pc_room = "PC방";

}
