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
        BoardListFragment boardListFragment = new BoardListFragment();
        return boardListFragment;
        // switch로 프래그먼트 교체하려고하니까 에러남ㅋ
//        switch(position){
//            case 0:
//                BoardListFragment boardFragment = new BoardListFragment();
////                Bundle bundle = new Bundle(2); // 파라미터는 전달할 데이터의 수
////                bundle.putString("latitude", Double.toString(gpsInfo.getLatitude()));
////                bundle.putString("longitude", Double.toString(gpsInfo.getLongitude()));
////                boardFragment.setArguments(bundle);
//                return boardFragment;
//            default:
//                return null;
//        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
