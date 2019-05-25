package com.practice.mvvmnotetakingapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.practice.mvvmnotetakingapp.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    /* insert single note into room database */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    /* insert multiple notes list like sample notes list into room database */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNotes(List<Note> notesList);

    /* delete specific note from room database */
    @Delete
    void deleteNote(Note note);

    /* get specific note by noteId from room database */
    @Query("select * from Note where noteId = :noteId")
    Note getNoteById(int noteId);

    /* get All notes from room database */
    @Query("select * from Note order by date desc")
    List<Note> getAllNotes();

    /* delete all notes from room database */
    @Query("delete from Note")
    int deleteAllNotes();

    /* count total no of notes present in room database */
    @Query("select count(*) from Note")
    int getNotesCount();
}
