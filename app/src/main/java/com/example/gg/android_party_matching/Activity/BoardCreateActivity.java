package com.example.gg.android_party_matching.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListPopupWindow;
import android.widget.Spinner;

import com.example.gg.android_party_matching.R;

import java.lang.reflect.Field;
import java.util.List;

public class BoardCreateActivity extends AppCompatActivity {
    private Spinner mSpinnerYear, mSpinnerMonth, mSpinnerDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_create);

        mSpinnerYear = (Spinner) findViewById(R.id.spYear);
        mSpinnerMonth = (Spinner) findViewById(R.id.spMonth);
        mSpinnerDay = (Spinner) findViewById(R.id.spDay);

        // 모임 날짜 월, 일 선택하는 dropdown 높이 조절
        try{
            Field popup = Spinner.class.getDeclaredField("mSpinnerMonth");
            popup.setAccessible(true);

            ListPopupWindow window = (ListPopupWindow)popup.get(mSpinnerMonth);
            window.setHeight(100);
//
//            popup = Spinner.class.getDeclaredField("mSpinnerDay");
//            popup.setAccessible(true);
//
//            window = (ListPopupWindow)popup.get(mSpinnerDay);
//            window.setHeight(100);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
