package com.example.gg.android_party_matching.member;

import com.google.gson.annotations.SerializedName;

public class MemberVO {

    private long member_code;
    private String email;
    private String pw;
    private String name;
    private String join_date;

    public long getMember_code() {
        return member_code;
    }

    public void setMember_code(long member_code) {
        this.member_code = member_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }
}
