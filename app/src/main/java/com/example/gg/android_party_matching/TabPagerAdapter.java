package com.example.gg.android_party_matching;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gg.android_party_matching.Activity.BoardTabActivity;
import com.example.gg.android_party_matching.Fragment.BoardListFragment;
import com.example.gg.android_party_matching.Fragment.WeatherFragment;
import com.example.gg.android_party_matching.Util.GpsInfo;
import com.example.gg.android_party_matching.member.BoardVO;

import java.util.ArrayList;
import java.util.List;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;
    GpsInfo gpsInfo;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                BoardListFragment boardListFragment = new BoardListFragment();
                return boardListFragment;
            case 1:
                WeatherFragment weatherFragment = new WeatherFragment();
                return weatherFragment;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
