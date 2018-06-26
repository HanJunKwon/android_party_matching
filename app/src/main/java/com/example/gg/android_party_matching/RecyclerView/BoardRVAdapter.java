package com.example.gg.android_party_matching.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gg.android_party_matching.Activity.BoardContentActivity;
import com.example.gg.android_party_matching.Activity.BoardTabActivity;
import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.data.RetrofitAPI;
import com.example.gg.android_party_matching.member.BoardVO;
import com.example.gg.android_party_matching.party.PartyVO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    List<PartyVO> party_list = new ArrayList<PartyVO>();

    public BoardRVAdapter(Context context, String latitude, String longitude){
        this.context = context;
        Call<List<PartyVO>> call_list = RetrofitAPI.getInstance().getPartyService().list(1, latitude, longitude, 1000);

        call_list.enqueue(new Callback<List<PartyVO>>() {
            @Override
            public void onResponse(Call<List<PartyVO>> call, Response<List<PartyVO>> response) {
                party_list = response.body();
                    for(PartyVO partyVO: party_list){

                }
//                if(response.isSuccessful()){
//                    for(PartyVO party: response.body()){
//                        party_list.add(party);
//                    }
//                }else{
//                    // 실패 시 처리
//                    System.out.print("error");
//                }
            }

            @Override
            public void onFailure(Call<List<PartyVO>> call, Throwable t) {
                Log.d("BoardRVAdapter error", ""+call);
            }
        });

//        BoardVO b1 = new BoardVO("2018년 6월 13일", "배틀그라운드 스쿼드 구함", 4, 2, "skclaqks11");
//        BoardVO b2 = new BoardVO("2018년 6월 12일", "아이온 보스 레이드", 4, 2, "dkdlel1019");
//
//        party_list.add(b1);
//        party_list.add(b2);
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
        PartyVO partyVO = party_list.get(position);
        holder.txtRegisterDate.setText("등록일자: "+partyVO.getMake_date());
        holder.txtTitle.setText(partyVO.getTitle());
        String max = Integer.toString(partyVO.getMax_people());
        String now = Integer.toString(partyVO.getJoin_people());
        holder.txtParticipantCount.setText("참가자 " + now + "/" + max);
        holder.txtRegistrant.setText("작성자: " + partyVO.getMaster_name());
    }

    @Override
    public int getItemCount() {
        return party_list.size();
    }
}
