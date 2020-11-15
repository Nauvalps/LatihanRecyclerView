package com.stmikbanisaleh.latihanrecyclerview;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Dosen.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DosenDao dosenDao();

    public static AppDatabase database;

    public static AppDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "db_dosen")
                    .enableMultiInstanceInvalidation()
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
            Log.i("Database Initialized", "Database Initialized");
        }
        return database;
    }
}
