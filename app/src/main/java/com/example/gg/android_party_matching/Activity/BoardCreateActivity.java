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
    private Spinner mSpinnerYear, mSpinnerMonth, mSpinnerDay, mSpinnerHour, mSpinnerMinute, mSpinnerMaxPeople;
    EditText edtTitle, edtContent;
    Button btnBoardRegistration;
    private String[] year=new String[2];
    private String[] month = new String[12];
    private String [] day = new String[31];

    private String[] hour = new String[24];
    private String[] minute = new String[60];

    private String[] maxPeople = new String[6];

    private String title, content, party_date, s_year, s_month, s_day, s_hour, s_minute, s_maxPeople, s_latitude, s_longiteu; // 제목, 내용, 파티하는 날짜

    SharedPreferences shared;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_create);

        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtContent = (EditText) findViewById(R.id.edtContent);
        mSpinnerYear = (Spinner) findViewById(R.id.spYear);
        mSpinnerMonth = (Spinner) findViewById(R.id.spMonth);
        mSpinnerDay = (Spinner) findViewById(R.id.spDay);
        mSpinnerHour = (Spinner) findViewById(R.id.spHour);
        mSpinnerMinute = (Spinner) findViewById(R.id.spMinute);
        mSpinnerMaxPeople = (Spinner) findViewById(R.id.spMaxPeople);
        btnBoardRegistration = (Button) findViewById(R.id.btnBoardRegistration);


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

        for(int i=0; i<maxPeople.length; ++i){
            maxPeople[i] = Integer.toString(i+1);
        }

        // 연도 스피너
        ArrayAdapter spinnerYearAdapter;
        spinnerYearAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, year);
        mSpinnerYear.setAdapter(spinnerYearAdapter);
        mSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s_year = (String)mSpinnerYear.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 월 스피너
        ArrayAdapter spinnerMonthAdapter;
        spinnerMonthAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, month);
        mSpinnerMonth.setAdapter(spinnerMonthAdapter);
        mSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s_month = (String)mSpinnerMonth.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 일자 선택
        ArrayAdapter spinnerDayAdapter;
        spinnerDayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, day);
        mSpinnerDay.setAdapter(spinnerDayAdapter);
        mSpinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s_day = (String)mSpinnerDay.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 시간 선택
        ArrayAdapter spinnerHourAdapter;
        spinnerHourAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, hour);
        mSpinnerHour.setAdapter(spinnerHourAdapter);
        mSpinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s_hour = (String)mSpinnerHour.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 분 선택
        ArrayAdapter spinnerMinuteAdapter;
        spinnerMinuteAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, minute);
        mSpinnerMinute.setAdapter(spinnerMinuteAdapter);
        mSpinnerMinute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s_minute = (String)mSpinnerMinute.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 최대 인원 스피너
        ArrayAdapter spinnerMaxPeopleAdapter;
        spinnerMaxPeopleAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, maxPeople);
        mSpinnerMaxPeople.setAdapter(spinnerMaxPeopleAdapter);
        mSpinnerMaxPeople.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s_maxPeople = (String)mSpinnerMaxPeople.getSelectedItem();
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

                party_date = s_year + "-" + s_month + "-" + s_day + " " + s_hour + ":" + s_minute;

                // 레트로핏 통신
                Call<String> login = RetrofitAPI.getInstance().getPartyService().make(1, jwt, title, content, s_maxPeople, party_date, "37.4480158", "126.6553101");
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
