package com.example.gg.android_party_matching.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gg.android_party_matching.Activity.BoardCreateActivity;
import com.example.gg.android_party_matching.Activity.BoardTabActivity;
import com.example.gg.android_party_matching.RecyclerView.BoardRVAdapter;
import com.example.gg.android_party_matching.R;

public class BoardListFragment extends Fragment implements View.OnClickListener {
    private RecyclerView         mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewBoard);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // 리사이클러 뷰의 어댑터를 설정
        mAdapter = new BoardRVAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabtnBoardAdd);
        floatingActionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fabtnBoardAdd:
                Intent intent = new Intent(getContext(), BoardCreateActivity.class);
                startActivity(intent);
                break;
                default:
                    break;

        }
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
}
