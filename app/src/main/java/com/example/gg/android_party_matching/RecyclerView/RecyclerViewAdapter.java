package com.example.gg.android_party_matching.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gg.android_party_matching.Activity.BoardTabActivity;
import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.Util.StaticUtil;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements StaticUtil {
    String data[];
    // itemView의 컴포넌트들에 대한 설정은 ViewHolder에서 한다. (ex. Button 클릭 이벤트)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button btnMenu;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            // onCreateViewHolder에서 만든 itemView 안에 있는 컴포넌트들을 설정한다.
            btnMenu = (Button) itemView.findViewById(R.id.btnMenu);
            btnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, BoardTabActivity.class);
                    int category = -1;
                    switch (getAdapterPosition()){
                        case 0:
                            category = 0;
                        case 1:
                            category = 1;
                        default:
                            // 에러
                    }

                    intent.putExtra("Category", category);
                    context.startActivity(intent);
                }
            });
        }
    }

    public RecyclerViewAdapter(){
        // 나중에 로컬 디비 생성하고 연결해서 목록을 가져온다
        data = new String[2];
        data[0] = StaticUtil.meal;
        data[1] = StaticUtil.pc_room;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 리사이클러 뷰 1줄에 들어가는 뷰(item)를 가져와서 itemView 변수에 저장
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        ViewHolder vh = new ViewHolder(itemView);
        return vh;
        // ViewHolder(View itemView)로 넘어간다.
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.btnMenu.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
