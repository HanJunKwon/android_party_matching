package com.example.gg.android_party_matching.member;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.gg.android_party_matching.CategoryActivity;
import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.Util.StaticUtil;
import com.example.gg.android_party_matching.data.RetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, StaticUtil {
    TextInputEditText edtEmail, edtPassword;
    TextView txtForgotPassword, txtSignUp;
    Button btnLogin;
    CheckBox chkAutoLogin;

    SharedPreferences shared;
    SharedPreferences.Editor editor;

    String email = "", password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("로그인"); // 타이틀바의 내용 변경(모든 버전에서 사용 가능)

        // SharedPreference
        // shared = getSharedPreferences("login", MODE_PRIVATE);
        // default로 하는게 쓰기 편함
        shared = PreferenceManager.getDefaultSharedPreferences(this);
        editor = shared.edit();

        // 자동 로그인 체크한 경우, 자동으로 로그인하게
        /*boolean isAutoLogin = shared.getBoolean(StaticUtil.isAutoLogin, false);
        if(isAutoLogin){
            email = shared.getString(StaticUtil.user_email,"");
            password = shared.getString(StaticUtil.user_password, "");
            // 로그인 성공
            if(checkAuth(email, password)){
                Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                startActivity(intent);
                finish();
            }
        }*/

        // 자동 로그인 체크 하지 않아도 자동로그인 됨 or 자동 로그인 체크 방식으로 수정 하셔도 될거 같아요..
        String jwt = shared.getString("jwt", null);
        if(jwt != null){
            Call<String> verify = RetrofitAPI.getInstance().getMemberService().verify(jwt);
            verify.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        String result = response.body();
                        if(result.equals("success")){
                            Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            // 실패처리 로그인페이지 이동
                        }
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    // 통신오류처리
                }
            });
        }

        edtEmail = (TextInputEditText) findViewById(R.id.edtEmail);
        edtPassword = (TextInputEditText) findViewById(R.id.edtPassword);

        // 로그인 버튼 설정
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        // 비밀번호 찾기 TextView 설정
        txtForgotPassword = (TextView)findViewById(R.id.txtForgotPassword);
        txtForgotPassword.setOnClickListener(this);

        // 회원가입 TextView 설정
        txtSignUp = (TextView) findViewById(R.id.txtSignUp);
        txtSignUp.setOnClickListener(this);

        // 자동 로그인 설정
        chkAutoLogin = (CheckBox)findViewById(R.id.chkAutoLogin);
        chkAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                SharedPreferences.Editor editor = shared.edit();
                if(isChecked){

                    // 체크한 경우 자동 로그인 true
                    editor.putBoolean(StaticUtil.isAutoLogin, true);
                }else{
                    // 체크 해제한 경우 자동 로그인 false
                    editor.putBoolean(StaticUtil.isAutoLogin, false);
                    // 체크 해제있으니까 email이랑 password와 관련된 정보 삭제
                    editor.remove(StaticUtil.user_email);
                    editor.remove(StaticUtil.user_password);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        if(view == btnLogin){
            // 이메일과 패스워드 값을 가져온다.
            email = edtEmail.getText().toString();
            password = edtPassword.getText().toString();

            // 레트로핏 통신
            Call<String> login = RetrofitAPI.getInstance().getMemberService().login(email, password);
            // 통신 리턴
            login.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    // 값 리턴 성공
                    if(response.isSuccessful()){
                        // 리턴값
                        String result = response.body();

                        if(result.equals("delete")){
                            // 탈퇴
                        }else if(result.equals("fail")){
                            // 아디 or 비번 틀림
                        }else{
                            // 성공
                            // JWT 넣어줌
                            editor.putString("jwt", result);
                            editor.commit();

                            // 메인으로 이동~~

                        }

                    }else{
                        // 값 리턴 실패
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    // 통신에러
                }
            });

        } else if(view == txtForgotPassword){

        } else if(view == txtSignUp){
            intent = new Intent(LoginActivity.this, SignUpActivity.class);
        }
    }

    private boolean checkAuth(String email, String passwrod){
        // 디비 접속해서 확인
        boolean isAuth = false;

        // 해당 유저가 존재하고 비밀번호가 맞다면 isAuth=true

        // 뷰단만 만들기 때문에 현재는 true 반환해서 액티비티 전환되는 것만 확인
        return true;
    }
}
