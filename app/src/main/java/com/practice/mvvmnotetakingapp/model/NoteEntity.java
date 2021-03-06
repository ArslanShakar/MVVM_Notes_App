package com.practice.mvvmnotetakingapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Note")
public class NoteEntity {

    @PrimaryKey(autoGenerate = true)
    private int noteId;
    private String note;
    private Date date;

    @Ignore
    public NoteEntity() {
    }

    @Ignore
    public NoteEntity(String note, Date date) {
        this.note = note;
        this.date = date;
    }

    public NoteEntity(int noteId, String note, Date date) {
        this(note, date);
        this.noteId = noteId;
    }

    public int getNoteId() {
        return noteId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
