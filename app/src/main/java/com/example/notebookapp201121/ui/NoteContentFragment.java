package com.example.notebookapp201121.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notebookapp201121.MainActivity;
import com.example.notebookapp201121.Navigation;
import com.example.notebookapp201121.Publisher;
import com.example.notebookapp201121.R;
import com.example.notebookapp201121.data.Note;

public class NoteContentFragment extends Fragment {

    public static final String KEY = "key";
    private Note note;
    private Publisher publisher;

    private TextView contentTextView;
    private TextView titleTextView;

    public static NoteContentFragment newInstance(Note note) {
        NoteContentFragment fragment = new NoteContentFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY, note);
        fragment.setArguments(args);
        return fragment;
    }

    public static NoteContentFragment newInstance() {
        NoteContentFragment fragment = new NoteContentFragment();
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
        View view = inflater.inflate(R.layout.fragment_note_content, container, false);
        setHasOptionsMenu(true);

        initView(view);

        if (note != null) {
            populateView();
        }
        return view;
    }

    private void initView(View view) {
        titleTextView = view.findViewById(R.id.title_text_view);
        contentTextView = view.findViewById(R.id.content_text_view);
    }

    private void populateView() {
        titleTextView.setText(note.getNoteTitle());
        contentTextView.setText(note.getNoteContent());
    }

        @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        publisher = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.content_back_button).setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        view.findViewById(R.id.content_edit_btn).setOnClickListener(v -> {
            Navigation navigation = new Navigation(requireActivity().getSupportFragmentManager());
            navigation.addFragment(R.id.fragment_container, NoteContentChildFragment.newInstance(note), true);
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

    @Override
    public void onStop() {
        super.onStop();
        note = collectNote();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        publisher.notifySingle(note);
    }

    private Note collectNote() {
        String title = this.titleTextView.getText().toString();
        String content = this.contentTextView.getText().toString();

        return new Note(title, content);
    }
}