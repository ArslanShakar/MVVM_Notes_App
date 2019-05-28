package com.practice.mvvmnotetakingapp.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.practice.mvvmnotetakingapp.database.AppDatabase;
import com.practice.mvvmnotetakingapp.model.NoteEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static final String TAG = "MyTag";
    public LiveData<List<NoteEntity>> notesList;
    private Executor executor = Executors.newSingleThreadExecutor();
    private AppDatabase databaseRef;

    private AppRepository(Context context) {
        databaseRef = AppDatabase.getInstance(context.getApplicationContext());
        notesList = getAllNotes();
    }

    public static AppRepository getInstance(Context context) {
        return new AppRepository(context);
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                databaseRef.noteDao().insertAllNotes(SampleDataProvider.getSampleNotesData());
            }
        });
    }

    private LiveData<List<NoteEntity>> getAllNotes() {
        return databaseRef.noteDao().getAllNotes();
    }

    public void deleteAllData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                int totalNotesDeleted = databaseRef.noteDao().deleteAllNotes();
                Log.d(TAG, "Notes Deleted : " + totalNotesDeleted);
            }
        });
    }

    public NoteEntity loadNote(int noteId) {
        return databaseRef.noteDao().getNoteById(noteId);
    }

    public void insertNote(final NoteEntity noteEntity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                databaseRef.noteDao().insertNote(noteEntity);
            }
        });
    }
}
