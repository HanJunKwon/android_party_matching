package com.example.gg.android_party_matching.Activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.TabPagerAdapter;

public class BoardTabActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager vpBoard;
    FloatingActionButton fabtnBoardAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_tab);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        vpBoard = (ViewPager) findViewById(R.id.vpBoard);

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
}
