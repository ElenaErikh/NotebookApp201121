package com.example.notebookapp201121;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitlesFragment extends Fragment {

    public static final String CURRENT_NOTE_TITLE = "CurrentTitle";
    public static final int DEF_VALUE = 0;
    private Note currentNote;
    private boolean isLand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_titles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE_TITLE);
        } else {
            currentNote = new Note(getResources().getStringArray(R.array.titles)[DEF_VALUE], DEF_VALUE);
        }

        if (isLand) {
            showNoteContentLand(currentNote);
        }

        initList(view);
    }

    private void initList(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        String[] titles = getResources().getStringArray(R.array.titles);

        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            TextView tvTitle = new TextView(getContext());
            tvTitle.setText(title);
            tvTitle.setTextSize(30);
            linearLayout.addView(tvTitle);
            final int fi = i;
            tvTitle.setOnClickListener(v -> {
                currentNote = new Note(getResources().getStringArray(R.array.titles)[fi], fi);
                showNoteContent(currentNote);
                updateText(currentNote);
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE_TITLE, currentNote);
        super.onSaveInstanceState(outState);
    }

    private void updateText(Note currentNote){
        LinearLayout linearLayout = (LinearLayout) getView();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i);
            textView.setBackgroundColor(Color.WHITE);
        }
        ((TextView) linearLayout.getChildAt(currentNote.getNoteContentIndex())).setBackgroundColor(Color.BLUE);
    }

    private void showNoteContent(Note currentNote) {
        if(isLand){
            showNoteContentLand(currentNote);
        } else {
            showNoteContentPort(currentNote);
        }
    }

    private void showNoteContentLand(Note currentNote) {
        NoteContentFragment contentFragment = NoteContentFragment.newInstance(currentNote);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_land, contentFragment)
                .commit();
    }

    private void showNoteContentPort(Note currentNote) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteContentActivity.class);
        intent.putExtra(NoteContentFragment.KEY, currentNote);
        startActivity(intent);
    }




}