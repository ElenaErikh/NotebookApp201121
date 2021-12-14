package com.example.notebookapp201121.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.notebookapp201121.R;

public class MyDialogFragment extends DialogFragment {

    public static final String TAG = "MyTAG";
    private Resources resource;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Activity activity = requireActivity();
        return new AlertDialog.Builder(activity)
                .setMessage(resource.getString(R.string.quitQwestion))
                .setPositiveButton(resource.getString(R.string.yes), (dialogInterface, i) -> {
                    activity.finish();
                    return;
                })
                .setNegativeButton(resource.getString(R.string.no), null)
                .create();


    }
}