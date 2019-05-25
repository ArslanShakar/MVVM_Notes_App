package com.practice.mvvmnotetakingapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.practice.mvvmnotetakingapp.model.Note;

@Database(entities = {Note.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "notes.db";

    /* Volatile means it only access from main memory */
    private static volatile AppDatabase instance;

    private static final Object LOCK = new Object();

    public abstract NoteDao noteDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
