package com.example.gg.android_party_matching;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    String data[];
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Button btnMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            btnMenu = (Button) itemView.findViewById(R.id.btnMenu);
        }
    }

    public RecyclerViewAdapter(){
        // 나중에 로컬 디비 생성하고 연결해서 목록을 가져온다
        data = new String[2];
        data[0] = "식사";
        data[1] = "PC방";
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 리사이클러 뷰에 들어가는 item에 대한 뷰를 생성한다
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
