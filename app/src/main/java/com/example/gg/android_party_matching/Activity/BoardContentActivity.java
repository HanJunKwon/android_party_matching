package com.example.gg.android_party_matching.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gg.android_party_matching.R;
import com.example.gg.android_party_matching.Util.StaticUtil;

public class BoardContentActivity extends AppCompatActivity implements View.OnClickListener, StaticUtil {
    TextView txtTitle, txtContent, txtTime, txtPlace, txtRegistrant;
    Button btnJoin;
    FloatingActionButton btnGroupMemberLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_content);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtContent = (TextView) findViewById(R.id.txtContent);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtPlace = (TextView) findViewById(R.id.txtPlace);

        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(this);

        btnGroupMemberLocation = (FloatingActionButton) findViewById(R.id.btnGroupMemberLocation);
        btnGroupMemberLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnJoin:
                // 그룹 참여 할 것인지 물어보는 dialog 보여줌
                AlertDialog.Builder builder = new AlertDialog.Builder(BoardContentActivity.this);
                builder.setMessage(StaticUtil.group_join)
                        .setPositiveButton("신청", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 그룹 가입 신청 한 경우 가입 신청까지 기다려 달라는 dialog를 보여줌
                                AlertDialog.Builder ok = new AlertDialog.Builder(BoardContentActivity.this);
                                ok.setMessage(StaticUtil.group_join_success)
                                        .setCancelable(true)
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        });
                                AlertDialog groupJoinSuccessDialog = ok.create();
                                groupJoinSuccessDialog.show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.btnGroupMemberLocation:

                break;
            default:
                return;
        }
    }
}
