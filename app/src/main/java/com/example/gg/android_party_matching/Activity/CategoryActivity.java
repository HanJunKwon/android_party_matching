package com.example.gg.android_party_matching.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.RecyclerViewAdapter;
import com.example.gg.android_party_matching.Util.GpsInfo;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView         mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // 리사이클러 뷰의 어댑터를 설정
        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // GPS 생성
        GpsInfo gpsInfo = new GpsInfo(this);
        gpsInfo.showSettingsAlert();
    }
}
