package com.example.gg.android_party_matching.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.gg.android_party_matching.Listener.DialogListener;
import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.Util.StaticUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, StaticUtil {
    TextInputEditText edtEmail, edtPassword;
    TextView txtForgotPassword, txtSignUp;
    Button btnLogin;
    CheckBox chkAutoLogin;

    SharedPreferences shared;

    String email = "", password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("로그인"); // 타이틀바의 내용 변경(모든 버전에서 사용 가능)

        // SharedPreference
        shared = getSharedPreferences("login", MODE_PRIVATE);

        // 자동 로그인 체크한 경우, 자동으로 로그인하게
        boolean isAutoLogin = shared.getBoolean(StaticUtil.isAutoLogin, false);
        if(isAutoLogin){
            email = shared.getString(StaticUtil.user_email,"");
            password = shared.getString(StaticUtil.user_password, "");
            // 로그인 성공
            if(checkAuth(email, password)){
                Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                startActivity(intent);
                finish();
            }
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

            // 로그인 성공
            if(checkAuth(email, password)){

                // 자동 로그인이 체크되어 있는 경우 SharedPreferences를 사용하여 자동로그인 여부, 이메일, 비밀번호 저장
                boolean isAutoLogin = shared.getBoolean("isAutoLogin", false); // 두번째 인자는 기본값으로 isAutoLogin이라는게 Key가 존재하지 않다면 2번째 인자를 반환
                if(isAutoLogin){
                    // SharedPreferences 참고 사이트 :
                    SharedPreferences.Editor editor = shared.edit();
                    // 데이터 저장은 Key와 Value형태로 저장한다.
                    editor.putString(StaticUtil.user_email, email); // 이메일 저장
                    editor.putString(StaticUtil.user_password, password); // 비밀번호 저장
                    editor.commit(); // 반드시 커밋해줘야 함
                }
                intent = new Intent(LoginActivity.this, CategoryActivity.class);
                startActivity(intent);
                finish();
            }
            // 로그인 실패
            else{

            }
        } else if(view == txtForgotPassword){

        } else if(view == txtSignUp){
            intent = new Intent(LoginActivity.this, SignUpActivity.class);
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
}
