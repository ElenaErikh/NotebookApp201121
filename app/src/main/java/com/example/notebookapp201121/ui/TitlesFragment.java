package com.example.notebookapp201121.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notebookapp201121.R;
import com.example.notebookapp201121.data.Note;
import com.example.notebookapp201121.data.NoteSource;
import com.example.notebookapp201121.data.NotesData;

import java.util.List;

public class TitlesFragment extends Utils {

    public static final String CURRENT_NOTE_TITLE = "CurrentTitle";
    public static final int DEF_VALUE = 0;
    private NoteSource data;
    private Note currentNote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_titles, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        NoteSource data = (NoteSource) new NotesData(getResources()).init();
        initRecyclerView(recyclerView, data);
        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView, NoteSource data) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        NoteAdapter adapter = new NoteAdapter(data);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((v, position) -> {
            showNoteContent(currentNote);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE_TITLE);
        } else {
            currentNote = new Note(getResources().getStringArray(R.array.titles)[DEF_VALUE],
                    null, getResources().getStringArray(R.array.contents)[DEF_VALUE]);
        }
    }

    private void showNoteContent(Note note) {

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof TitlesFragment && fragment.isVisible()) {
                fragmentManager.beginTransaction()
                        .remove(fragment)
                        .replace(R.id.fragment_container, NoteContentFragment.newInstance(note))
                        .addToBackStack("")
                        .commit();
            }
        }

        Log.d("Fragment Titles", "Add to backstack");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("Fragment Titles", "Finish");

    }


}