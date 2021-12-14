package com.example.notebookapp201121.ui;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notebookapp201121.R;
import com.example.notebookapp201121.data.Note;

public class NoteContentFragment extends Utils {

    public static final String KEY = "key";
    private Note note;

    public static NoteContentFragment newInstance(Note note) {
        NoteContentFragment fragment = new NoteContentFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if(getArguments().getParcelable(KEY) instanceof Note) {
                note = getArguments().getParcelable(KEY);
            }
        }
        if (savedInstanceState != null) {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_note_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (note == null) {
            return;
        }

        TextView contentTextView = view.findViewById(R.id.content_text_view);

        TypedArray contents = getResources().obtainTypedArray(R.array.contents);
        contentTextView.setText(contents.getString(note.getNoteContentIndex()));

        TextView titleTextView = view.findViewById(R.id.title_text_view);
        titleTextView.setText(note.getNoteTitle());
        contents.recycle();

        view.findViewById(R.id.content_back_button).setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        view.findViewById(R.id.content_edit_btn).setOnClickListener(v -> {
            getChildTrans()
                    .replace(R.id.content_child_container, NoteContentChildFragment.newInstance(note))
                    .addToBackStack("")
                    .commit();
        });

        Log.d("Fragment NoteContent", "Start");
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.action_add);
        if (item != null) {
            item.setVisible(false);
        }

        menu.add(Menu.NONE, 20, Menu.NONE, "Item menu");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("Fragment NoteContent", "Finish");

    }

}