package com.example.notebookapp201121;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class NoteContentChildFragment extends Fragment {

    public static final String CHILD_INDEX = "index";

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
        return inflater.inflate(R.layout.fragment_note_content_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments != null) {
            Note note = arguments.getParcelable(CHILD_INDEX);

            EditText noteEditText = view.findViewById(R.id.child_content_edit_text);

            TypedArray contents = getResources().obtainTypedArray(R.array.contents);
            noteEditText.setText(contents.getString(note.getNoteContentIndex()));

            EditText titleEditText = view.findViewById(R.id.child_title_edit_text);
            titleEditText.setText(note.getNoteTitle());
            contents.recycle();
        }

        view.findViewById(R.id.child_cancel_button).setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });

        view.findViewById(R.id.child_save_btn).setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();

            if (arguments == null) {
                MyNotificationResult notificationResult = (MyNotificationResult) requireActivity();
                notificationResult.onSnackBarResult("Your note was successfully saved!");
            } else {
                Snackbar.make(getParentFragment().getView().findViewById(R.id.note_container),
                        "Changes were successfully saved!", Snackbar.LENGTH_SHORT).show();
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