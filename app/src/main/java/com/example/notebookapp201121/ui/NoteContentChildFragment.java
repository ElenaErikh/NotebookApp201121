package com.example.notebookapp201121.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notebookapp201121.R;
import com.example.notebookapp201121.data.Note;
import com.google.android.material.snackbar.Snackbar;

public class NoteContentChildFragment extends Fragment {

    private EditText noteEditText;
    private EditText titleEditText;
    private Note note;

    public static final String CHILD_INDEX = "index";
    private Resources resource;

    public static NoteContentChildFragment newInstance(Note note) {
        NoteContentChildFragment fragment = new NoteContentChildFragment();
        Bundle args = new Bundle();
        args.putParcelable(CHILD_INDEX, note);
        if (note.getNoteTitle() == null){
            return fragment;
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_content_child, container, false);
        setHasOptionsMenu(true);
        Bundle arguments = getArguments();

        initView(view);

        if (arguments != null) {
            note = arguments.getParcelable(CHILD_INDEX);
            populateView();
        }

        return view;
    }

    private void initView(View view) {
        noteEditText = view.findViewById(R.id.child_content_edit_text);
        titleEditText = view.findViewById(R.id.child_title_edit_text);
    }

    private void populateView() {
        titleEditText.setText(note.getNoteTitle());
        noteEditText.setText(note.getNoteContent());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();

        view.findViewById(R.id.child_cancel_button).setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        view.findViewById(R.id.child_save_btn).setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();

            if (arguments == null) {
                MyNotificationResult notificationResult = (MyNotificationResult) requireActivity();
                notificationResult.onSnackBarResult(getResources().getString(R.string.saveNote));
            } else {
                Snackbar.make(view,
                        getResources().getString(R.string.saveChanges), Snackbar.LENGTH_SHORT).show();
            }
        });

        Log.d("Fragment Editable", "Start");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("Fragment Editable", "Finish");

    }

}