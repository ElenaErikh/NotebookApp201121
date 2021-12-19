package com.example.notebookapp201121.data;

public interface NoteSource{
    Note getNote (int position);
    int listSize ();
    void deleteNote(int position);
    void updateNote(int position, Note note);
    void addNote(Note note);
    void clearNote();

}
