package com.example.notebookapp201121.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Utils extends Fragment {

    @NonNull
    FragmentTransaction getChildTrans() {
        return getChildFragmentManager()
                .beginTransaction();
    }

}
