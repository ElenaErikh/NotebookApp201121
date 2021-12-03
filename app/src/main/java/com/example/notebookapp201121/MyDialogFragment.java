package com.example.notebookapp201121;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    public static final String TAG = "MyTAG";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Activity activity = requireActivity();
        return new AlertDialog.Builder(activity)
                .setMessage("Do you want to quit?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    activity.finish();
                    return;
                })
                .setNegativeButton("No", null)
                .create();


    }
}