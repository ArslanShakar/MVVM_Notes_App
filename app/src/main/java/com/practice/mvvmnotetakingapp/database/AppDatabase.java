package com.practice.mvvmnotetakingapp.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.practice.mvvmnotetakingapp.model.NoteEntity;

@Database(entities = {NoteEntity.class}, version = 4)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "notes.db";

    /* Volatile means it only access from main memory */
    private static volatile AppDatabase instance;

    private static final Object LOCK = new Object();

    public abstract NoteDao noteDao();

    /*
    * Read this link what is migration and when we needed..must handle migrations.
    * https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
    * */
    static final Migration MIGRATION_1_2 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).
                            addMigrations(MIGRATION_1_2).
                            //OR
                            // .fallbackToDestructiveMigration().
                            build();
                }
            }
        }
        return instance;
    }
}
