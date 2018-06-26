package com.example.gg.android_party_matching.Activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.TabPagerAdapter;
import com.example.gg.android_party_matching.Util.GpsInfo;

public class BoardTabActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager vpBoard;
    long pressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_tab);
        getSupportActionBar().setTitle("소모임"); // 타이틀바의 내용 변경(모든 버전에서 사용 가능)

        // GPS 생성
        GpsInfo gpsInfo = new GpsInfo(this);
        gpsInfo.showSettingsAlert();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        vpBoard = (ViewPager) findViewById(R.id.vpBoard);

        tabLayout.addTab(tabLayout.newTab().setText("게시글"));
        tabLayout.addTab(tabLayout.newTab().setText("날씨"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        vpBoard.setAdapter(pagerAdapter);
        vpBoard.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpBoard.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(pressedTime == 0){
            Toast.makeText(this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }
        else{
            int second = (int) (System.currentTimeMillis() - pressedTime);
            if(second > 2000){
                Toast.makeText(this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                pressedTime = 0;
            }
            else{
                super.onBackPressed();
                finish();
            }
        }
    }
}
