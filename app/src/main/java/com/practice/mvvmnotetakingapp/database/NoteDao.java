package com.practice.mvvmnotetakingapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.practice.mvvmnotetakingapp.model.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {

    /* insert single noteEntity into room database */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity noteEntity);

    /* insert multiple notes list like sample notes list into room database */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNotes(List<NoteEntity> notesList);

    /* delete specific noteEntity from room database */
    @Delete
    void deleteNote(NoteEntity noteEntity);

    /* get specific note by noteId from room database */
    @Query("select * from Note where noteId = :noteId")
    NoteEntity getNoteById(int noteId);

    /* get All notes from room database */
    @Query("select * from Note order by date desc")
    LiveData<List<NoteEntity>> getAllNotes();

    /* delete all notes from room database */
    @Query("delete from Note")
    int deleteAllNotes();

    /* count total no of notes present in room database */
    @Query("select count(*) from Note")
    int getNotesCount();
}
