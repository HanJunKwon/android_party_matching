package com.example.gg.android_party_matching;

import com.google.gson.annotations.SerializedName;

public class Member {
    @SerializedName("email") private String email;
    @SerializedName("password") private String password;
    private String token;

    public Member() {
        email = "";
        password = "";
        token = "";
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
