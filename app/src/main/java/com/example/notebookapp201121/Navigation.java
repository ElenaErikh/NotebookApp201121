package com.example.notebookapp201121;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Navigation {

    private  final FragmentManager fragmentManager;

    public Navigation(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void addFragment(int containerViewId, Fragment fragment, boolean useBackStack) {
       FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(containerViewId, fragment);
        if (useBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }











}
