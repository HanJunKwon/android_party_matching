package com.example.gg.android_party_matching.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.example.gg.android_party_matching.Listener.DialogListener;
import com.example.gg.android_party_matching.Util.StaticUtil;

public class BaseDialogFragment extends DialogFragment {
    /**
     * DIALOG_MESSAGE의 값은 Budle에서 사용할 키값을 저장한다. key값은 value값을 갖는 변수의 변수명이다
     */
    public static final String DIALOG_MESSAGE ="dialogMessage";
    String dialogMessage = "not set";

    // DialogListener 인터페이스를 멤버 변수로 선언해준다.
    private DialogListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // getArguments()는 이 Dialog를 호출한 액티비티가 전달하는 argument 값을 가져온다
        Bundle bundle = getArguments();
        if(bundle != null){
            dialogMessage = bundle.getString(DIALOG_MESSAGE);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

        builder.setMessage(dialogMessage)
                .setPositiveButton(StaticUtil.dialog_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dialogMessage.equals(StaticUtil.SignUp)){
                            // 다이얼로그 리너스인터페이스의 메소드를 호출해 값을 SignUpActivity로 넘겨준다
                            listener = (DialogListener) getActivity();
                            listener.onPositiveClicked(true);
                            dismiss();
                        }
                    }
                })
                .setNegativeButton(StaticUtil.dialog_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        return builder.create();
    }
}
