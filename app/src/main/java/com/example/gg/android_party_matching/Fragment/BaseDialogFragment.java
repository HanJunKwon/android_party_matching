package com.example.gg.android_party_matching.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gg.android_party_matching.Util.StaticUtil;

public class BaseDialogFragment extends DialogFragment {
    /**
     * DIALOG_MESSAGE의 값은 Budle에서 사용할 키값을 저장한다. key값은 value값을 갖는 변수의 변수명이다
     */
    public static final String DIALOG_MESSAGE ="dialogMessage";
    String dialogMessage = "not set";

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
