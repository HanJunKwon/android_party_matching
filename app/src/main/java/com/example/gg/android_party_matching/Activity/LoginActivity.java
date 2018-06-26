package com.example.gg.android_party_matching.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gg.android_party_matching.Listener.DialogListener;
import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.Util.StaticUtil;
import com.example.gg.android_party_matching.data.RetrofitAPI;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, StaticUtil {
    RelativeLayout relativeLayout1, relativeLayout2;
    TextInputEditText edtEmail, edtPassword;
    TextView txtForgotPassword, txtSignUp, txtLoading;
    Button btnLogin;
    ProgressBar progressBar;

    SharedPreferences shared;
    SharedPreferences.Editor editor;

    String email = "", password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("로그인"); // 타이틀바의 내용 변경(모든 버전에서 사용 가능)

        relativeLayout1 = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.RelativeLayout2);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.GONE);
        txtLoading = (TextView) findViewById(R.id.txtLoading);
        txtLoading.setVisibility(View.GONE);

        // SharedPreference
        shared = getSharedPreferences("login", MODE_PRIVATE);
        editor = shared.edit();
        // AsyncTask를 통해 HttpURLConnection 수행


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
                            // 로딩 아이콘만 보여준다.
                            relativeLayout1.setVisibility(View.GONE);
                            relativeLayout2.setVisibility(View.GONE);

                            // 로그인 로딩을 보여준다. 딜레이를 줘서 로딩 중이라는 화면을 보여주는 것이 좋다
                            progressBar.setVisibility(View.VISIBLE);
                            txtLoading.setVisibility(View.VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(LoginActivity.this, BoardTabActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 1500);
                            editor.commit();
                        }else{
                            // 실패처리 로그인페이지 이동
                            Toast.makeText(LoginActivity.this, StaticUtil.server_connection_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    // 통신오류처리
                    Toast.makeText(LoginActivity.this, StaticUtil.server_connection_error, Toast.LENGTH_SHORT).show();
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


    }

    @Override
    public void onClick(View view) {
//        Intent intent;
//        intent = new Intent(LoginActivity.this, BoardTabActivity.class);
//        startActivity(intent);
//        finish();

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
                            Toast.makeText(LoginActivity.this, StaticUtil.user_widthdraw, Toast.LENGTH_SHORT).show();
                        }else if(result.equals("fail")){
                            // 아디 or 비번 틀림
                            Toast.makeText(LoginActivity.this, StaticUtil.check_email_or_password, Toast.LENGTH_SHORT).show();
                        }else{
                            // 성공
                            // 로딩 아이콘만 보여준다.
                            relativeLayout1.setVisibility(View.GONE);
                            relativeLayout2.setVisibility(View.GONE);

                            // 로그인 로딩을 보여준다. 딜레이를 줘서 로딩 중이라는 화면을 보여주는 것이 좋다
                            progressBar.setVisibility(View.VISIBLE);
                            txtLoading.setVisibility(View.VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(LoginActivity.this, BoardTabActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 1500);

                            // JWT 넣어줌
                            editor.putString("jwt", result);
                            editor.commit();
                        }
                    }else{
                        // 값 리턴 실패
                        Toast.makeText(LoginActivity.this, StaticUtil.server_connection_error, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    // 통신에러
                    Toast.makeText(LoginActivity.this, StaticUtil.server_connection_error, Toast.LENGTH_SHORT).show();
                    return;
                }
            });

        } else if(view == txtForgotPassword){
            Log.e("forgotPassword","log");

        } else if(view == txtSignUp){
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkAuth(String email, String passwrod){
        // 디비 접속해서 확인
        boolean isAuth = false;

        // 해당 유저가 존재하고 비밀번호가 맞다면 isAuth=true

        // 뷰단만 만들기 때문에 현재는 true 반환해서 액티비티 전환되는 것만 확인
        return true;
    }

    private class NetworkTask {
    }
}
