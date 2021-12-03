package com.example.notebookapp201121;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class NoteContentFragment extends Fragment {

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
            note = getArguments().getParcelable(KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText noteEditText = view.findViewById(R.id.contentEditText);
        TypedArray contents = getResources().obtainTypedArray(R.array.contents);
        noteEditText.setText(contents.getString(note.getNoteContentIndex()));
        EditText titleEditText = view.findViewById(R.id.titleEditText);
        titleEditText.setText(note.getNoteTitle());
        contents.recycle();

    }
}