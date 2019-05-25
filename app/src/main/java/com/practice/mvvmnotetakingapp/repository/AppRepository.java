package com.practice.mvvmnotetakingapp.repository;

import android.content.Context;

import com.practice.mvvmnotetakingapp.database.AppDatabase;
import com.practice.mvvmnotetakingapp.model.Note;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AppRepository {
    private static AppRepository ourInstance;
    public List<Note> notesList;
    private Executor executor = Executors.newSingleThreadExecutor();
    private AppDatabase databaseRef;

    private AppRepository(Context context) {
        notesList = SampleDataProvider.getSampleNotesData();
        databaseRef = AppDatabase.getInstance(context.getApplicationContext());
    }

    public static AppRepository getInstance(Context context) {
        return ourInstance = new AppRepository(context);
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                databaseRef.noteDao().insertAllNotes(notesList);
            }
        });
    }
}
