package com.example.gg.android_party_matching.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.data.RetrofitAPI;

import java.lang.reflect.Field;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardCreateActivity extends AppCompatActivity {
    private Spinner mSpinnerYear, mSpinnerMonth, mSpinnerDay, mSpinnerHour, mSpinnerMinute;
    EditText edtTitle, edtContent;
    Button btnBoardRegistration;
    private String[] year=new String[2];
    private String[] month = new String[12];
    private String [] day = new String[31];

    private String[] hour = new String[24];
    private String[] minute = new String[60];

    private String title, content, party_date; // 제목, 내용, 파티하는 날짜
    private int max_people; // 최대인원

    SharedPreferences shared;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_create);

        year[0] = "2018";
        year[1] = "2019";

        for(int i=0; i<month.length; ++i){
            month[i] = Integer.toString(i+1);
        }

        for(int i=0; i<day.length; ++i){
            day[i] = Integer.toString(i+1);
        }

        for(int i= 0; i<hour.length; ++i){
            hour[i] = Integer.toString(i);
        }

        for(int i=0; i<minute.length; ++i){
            minute[i] = Integer.toString(i);
        }

        mSpinnerYear = (Spinner) findViewById(R.id.spYear);
        mSpinnerMonth = (Spinner) findViewById(R.id.spMonth);
        mSpinnerDay = (Spinner) findViewById(R.id.spDay);
        mSpinnerHour = (Spinner) findViewById(R.id.spHour);
        mSpinnerMinute = (Spinner) findViewById(R.id.spMinute);
        btnBoardRegistration = (Button) findViewById(R.id.btnBoardRegistration);

        ArrayAdapter spinnerYearAdapter;
        spinnerYearAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, year);
        mSpinnerYear.setAdapter(spinnerYearAdapter);
        mSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter spinnerMonthAdapter;
        spinnerMonthAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, month);
        mSpinnerMonth.setAdapter(spinnerMonthAdapter);
        mSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter spinnerDayAdapter;
        spinnerDayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, day);
        mSpinnerDay.setAdapter(spinnerDayAdapter);
        mSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter spinnerHourAdapter;
        spinnerHourAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, hour);
        mSpinnerHour.setAdapter(spinnerHourAdapter);
        mSpinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter spinnerMinuteAdapter;
        spinnerMinuteAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, minute);
        mSpinnerMinute.setAdapter(spinnerMinuteAdapter);
        mSpinnerMinute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBoardRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = edtTitle.getText().toString();
                content = edtContent.getText().toString();

                // 제목 미입력
                if(title.isEmpty()){
                    Toast.makeText(BoardCreateActivity.this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(content.isEmpty()){
                    Toast.makeText(BoardCreateActivity.this, "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // SharedPreference
                shared = getSharedPreferences("login", MODE_PRIVATE);
                editor = shared.edit();

                String jwt = shared.getString("jwt", null);
                // 레트로핏 통신
                Call<String> login = RetrofitAPI.getInstance().getPartyService().make(1, jwt, title, content, "4", "2018-06-25 12:33", "37.4480158", "126.6553101");
                login.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()) {
                            String result = response.body();
                            if (result.equals("success")) {
                                Toast.makeText(BoardCreateActivity.this, "글 등록 완료", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
