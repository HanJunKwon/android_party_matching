package com.example.gg.android_party_matching.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gg.android_party_matching.Fragment.BaseDialogFragment;
import com.example.gg.android_party_matching.Listener.DialogListener;
import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.Util.StaticUtil;
import com.example.gg.android_party_matching.data.RetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 액티비티와 다이얼로그 사이의 리스너를 인터페이스로 만들어서 데이터를 주고 받는 소스코드 참고 사이트
 * http://liveonthekeyboard.tistory.com/143
 * http://android-coding.blogspot.com/2012/07/dialogfragment-with-interface-to-pass.html
 * https://bonoogi.postype.com/post/702
 */


// 회원가입 액티비티
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, StaticUtil, DialogListener {
    TextInputEditText edtName, edtEmail, edtPassword, edtConfirmPassword, edtKakaoTalkId;
    Button btnSignUp;

    String name, email, password, confirm_password, kakaoTalkId;
    public static final String DIALOG_MESSAGE ="dialogMessage";
    String dialogMessage = "not set";

    private DialogListener listener;

    public boolean isSuccess = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("회원가입"); // 타이틀바의 내용 변경(모든 버전에서 사용 가능)

        edtName = (TextInputEditText) findViewById(R.id.edtName);
        edtEmail = (TextInputEditText) findViewById(R.id.edtEmail);
        edtPassword = (TextInputEditText) findViewById(R.id.edtPassword);
        edtConfirmPassword = (TextInputEditText) findViewById(R.id.edtConfirmPassword);
        edtKakaoTalkId = (TextInputEditText) findViewById(R.id.edtKakaoTalkId);

        // 회원가입 버튼 설정
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // 회원가입 버튼을 누른 경우
        if(view == btnSignUp){
            // 데이터 null 검증
            if(ckhComponentEmpty()){
                // 비밀번호와 확인 비밀번호가 일치 여부 확인
                if(chkConfirmPassword()){
                    // DB의 member 테이블에서 유저 정보가 동일한 정보가 존재하는지 조회
                    if(chkOverlapMember()){
                        // 서버에 연결해서 회원가입 등록
                        BaseDialogFragment dialog = new BaseDialogFragment();

                        Bundle args = new Bundle();
                        args.putString(DIALOG_MESSAGE, StaticUtil.signUp);
                        dialog.setArguments(args);

                        dialog.show(getSupportFragmentManager(), "Test");
                    }
                }
            }
        }
    }

    // 회원가입 시 미입력된 데이터가 있는 지 검증하는 메소드
    private boolean ckhComponentEmpty() {
        // false : 검증 결과 문제 있음
        boolean isEmpty = false;
        name = edtName.getText().toString();
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
        confirm_password = edtConfirmPassword.getText().toString();
        kakaoTalkId = edtKakaoTalkId.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "이메일은 필수 입력사항입니다.", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "이름은 필수입력사항입니다.", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "비밀번호는 필수입력사항입니다.", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(confirm_password)){
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(kakaoTalkId)){
            Toast.makeText(this, "카카오톡 아이디는 필수입력사항입니다.", Toast.LENGTH_SHORT).show();
        } else{
            // 모든 editText에 데이터가 입력되어 있는 경우 isEmpty의 값을 true로 변경
            isEmpty = !isEmpty;
        }

        return isEmpty;
    }

    // 비밀번호와 확인 비밀번호의 텍스트 값이 일치하는지 확인
    private boolean chkConfirmPassword() {
        // true : 값 일치
        boolean isSame = false;
        // 비밀버호와 확인 비밀번호가 일치하면
        if(password.equals(confirm_password)){
            isSame = true;
        }
        return isSame;
    }

    // 서버에 중복되는 email과 kakaoTalkID가 있는지 확인
    private boolean chkOverlapMember(){
        // false 중복
        boolean isOverlap = false;
        // 서버에서 email, kakaotalkid 를 가져와 중복되는 데이터가 있는 확인
        // 중복되는 데이터가 없다면 true로 변경
        isOverlap = !isOverlap;
        return isOverlap;
    }

    @Override
    public void onPositiveClicked(boolean positive) {
        // 회원가입 여부에 확인을 누른 경우 SignUpActivity에서 처리
        if(positive){
            // 이메일 중복 여부 체크 후에 조인을 해야 한다.  URL : /member/email_check -> URL : /member/join

            // 이메일 중복 여부 체크하는 로직
            // 레트로핏 통신
            Call<String> email_check = RetrofitAPI.getInstance().getMemberService().email_check(email);
            // 통신 리턴
            email_check.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    // 값 리턴 성공
                    if(response.isSuccessful()){
                        // 리턴값
                        String result = response.body();
                        if(result.equals("0")){
                            // 중복되는 이메일 존재
                            Toast.makeText(SignUpActivity.this, StaticUtil.email_overlap, Toast.LENGTH_SHORT).show();
                        }else{
                            // 중복되는 이메일이 존재하지 않음

                            // 회원가입하는 로직
                            // 레트로핏 통신
                            Call<String> join = RetrofitAPI.getInstance().getMemberService().join(email, password, name);
                            // 통신 리턴
                            join.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    // 값 리턴 성공
                                    if(response.isSuccessful()){
                                        // 리턴값
                                        String result = response.body();
                                        if(result.equals("fail")){
                                            // 회원가입 실패
                                            Toast.makeText(SignUpActivity.this, "회원가입을 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                        }else{
                                            //회원가입 성공
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                            builder.setMessage(StaticUtil.signUpSuccess)
                                                    .setCancelable(false)
                                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    });
                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        }

                                    }else{
                                        // 값 리턴 실패
                                        Toast.makeText(SignUpActivity.this, StaticUtil.server_connection_error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    // 통신에러
                                    Toast.makeText(SignUpActivity.this, StaticUtil.server_connection_error, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }else{
                        // 값 리턴 실패
                        Toast.makeText(SignUpActivity.this, StaticUtil.server_connection_error, Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    // 통신에러
                    Toast.makeText(SignUpActivity.this, StaticUtil.server_connection_error, Toast.LENGTH_SHORT).show();
                }
            });



        }
    }

    @Override
    public void onNegativeClicked() {

    }
}
