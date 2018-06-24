package com.example.gg.android_party_matching.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gg.android_party_matching.Activity.BoardContentActivity;
import com.example.gg.android_party_matching.Activity.BoardTabActivity;
import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.member.BoardVO;

import java.util.ArrayList;

public class BoardRVAdapter extends RecyclerView.Adapter<BoardRVAdapter.ViewHolder> {
    Context context;
    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtRegisterDate, txtTitle, txtParticipantCount, txtRegistrant;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context = itemView.getContext();
                    Intent intent = new Intent(context, BoardContentActivity.class);
                    context.startActivity(intent);
                }
            });
            txtRegisterDate = (TextView) itemView.findViewById(R.id.txtRegisterDate);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtParticipantCount = (TextView) itemView.findViewById(R.id.txtParticipantCount);
            txtRegistrant = (TextView) itemView.findViewById(R.id.txtRegistrant);
        }
    }

    // 후에 BoardVO객체를 생성하여 생성자 호출 시 바인딩
    ArrayList<BoardVO> party_list = new ArrayList<BoardVO>();

    public BoardRVAdapter(Context context){
        //
        this.context = context;
        BoardVO b1 = new BoardVO("2018년 6월 13일", "배틀그라운드 스쿼드 구함", 4, 2, "skclaqks11");
        BoardVO b2 = new BoardVO("2018년 6월 12일", "아이온 보스 레이드", 4, 2, "dkdlel1019");

        party_list.add(b1);
        party_list.add(b2);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BoardVO board = party_list.get(position);
        holder.txtRegisterDate.setText(holder.txtRegisterDate.getText() + board.getRegisterDate());
        holder.txtTitle.setText(board.getTitle());
        String max = Integer.toString(board.getMax_participant());
        String now = Integer.toString(board.getNow_participant());
        holder.txtParticipantCount.setText(holder.txtParticipantCount.getText() + now + "/" + max);
        holder.txtRegistrant.setText(holder.txtRegistrant.getText() + board.getRegistrant());
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
