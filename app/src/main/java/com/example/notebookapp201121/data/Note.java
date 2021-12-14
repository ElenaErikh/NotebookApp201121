package com.example.notebookapp201121.data;

import android.os.Parcel;
import android.os.Parcelable;

public final class Note implements Parcelable {

    private String noteTitle;
    private String noteDate;
    private String noteContent;
    private int noteContentIndex;

    public Note() {

    }

    public Note(String noteTitle, String noteDate, String noteContent) {
        this.noteTitle = noteTitle;
        this.noteDate = noteDate;
        this.noteContent = noteContent;
    }

    protected Note(Parcel in) {
        noteTitle = in.readString();
        noteDate = in.readString();
        noteContent = in.readString();
        noteContentIndex = in.readInt();
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(noteTitle);
        parcel.writeString(noteDate);
        parcel.writeString(noteContent);
        parcel.writeInt(noteContentIndex);
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public int getNoteContentIndex() {
        return noteContentIndex;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
