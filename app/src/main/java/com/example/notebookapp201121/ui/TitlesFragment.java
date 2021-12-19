package com.example.notebookapp201121.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.notebookapp201121.Navigation;
import com.example.notebookapp201121.Publisher;
import com.example.notebookapp201121.R;
import com.example.notebookapp201121.data.Note;
import com.example.notebookapp201121.data.NoteSource;
import com.example.notebookapp201121.data.NotesData;

import java.util.List;

public class TitlesFragment extends Fragment {

    public static final String CURRENT_NOTE_TITLE = "CurrentTitle";
    public static final int DEF_VALUE = 0;
    private Note currentNote;

    private NoteSource data;
    private NoteAdapter adapter;
    private RecyclerView recyclerView;
    private Navigation navigation;
    private Publisher publisher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_titles, container, false);

        initViews(view);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuPosition();
        switch (item.getItemId()) {
            case R.id.edit:
                Navigation navigation = new Navigation(requireActivity().getSupportFragmentManager());
                navigation.addFragment(R.id.fragment_container,
                        NoteContentChildFragment.newInstance(data.getNote(position)), true);
                adapter.notifyItemChanged(position);
                return true;
            case R.id.delete:
                data.deleteNote(position);
                adapter.notifyItemRemoved(position);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.notes_menu, menu);
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        data = new NotesData(getResources()).init();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);

        adapter = new NoteAdapter(data, this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener((v, position) -> {
            showNoteContent(data.getNote(position));
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE_TITLE);
        } else {
            currentNote = new Note(getResources().getStringArray(R.array.titles)[DEF_VALUE],
                    getResources().getStringArray(R.array.contents)[DEF_VALUE]);
        }
    }

    private void showNoteContent(Note note) {

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof TitlesFragment && fragment.isVisible()) {
                Navigation navigation = new Navigation(fragmentManager);
                navigation.addFragment(R.id.fragment_container, NoteContentFragment.newInstance(note), true);
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