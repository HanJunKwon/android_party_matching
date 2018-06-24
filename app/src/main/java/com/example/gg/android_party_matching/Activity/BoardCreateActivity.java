package com.example.gg.android_party_matching.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gg.android_party_matching.R;

import java.lang.reflect.Field;
import java.util.List;

public class BoardCreateActivity extends AppCompatActivity {
    private Spinner mSpinnerYear, mSpinnerMonth, mSpinnerDay, mSpinnerHour, mSpinnerMinute;
    private String[] year=new String[2];
    private String[] month = new String[12];
    private String [] day = new String[31];

    private String[] hour = new String[24];
    private String[] minute = new String[60];

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

    }
}
