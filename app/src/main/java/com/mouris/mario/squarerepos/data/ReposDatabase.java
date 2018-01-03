package com.mouris.mario.squarerepos.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Repo.class}, version = 1, exportSchema = false)
public abstract class ReposDatabase extends RoomDatabase{
    public abstract ReposDao repoDao();

    private static final String DATABASE_NAME = "Repositories.db";

    static volatile ReposDatabase sInstance;

    public static ReposDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ReposDatabase.class, ReposDatabase.DATABASE_NAME).build();
        }
        return sInstance;
    }
}
