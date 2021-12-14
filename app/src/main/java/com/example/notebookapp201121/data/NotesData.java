package com.example.notebookapp201121.data;

import android.content.res.Resources;

import com.example.notebookapp201121.R;

import java.util.ArrayList;
import java.util.List;

public class NotesData implements NoteSource {

    private List<Note> noteList;
    private Resources resource;

    public NotesData(Resources resource) {
        this.resource = resource;
        noteList = new ArrayList<>();
    }

    public NotesData init() {
        String[] titles = resource.getStringArray(R.array.titles);
        String[] content = resource.getStringArray(R.array.contents);

        for (int i = 0; i < titles.length; i++) {
            noteList.add(new Note(titles[i], null, content[i]));
        }
        return this;
    }

    @Override
    public Note getNote(int position) {
        return noteList.get(position);
    }

    @Override
    public int listSize() {
        return noteList.size();
    }

}
