package com.example.notebookapp201121.data;

import android.os.Parcel;
import android.os.Parcelable;

public final class Note implements Parcelable {

    private String noteTitle;
    private String noteContent;

    public Note(){
    }

    public Note(String noteTitle, String noteContent) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    protected Note(Parcel in) {
        noteTitle = in.readString();
        noteContent = in.readString();
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
        parcel.writeString(noteContent);
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

}
