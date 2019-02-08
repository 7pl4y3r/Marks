package com.apps.a7pl4y3r.marks.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Mark.class}, version = 1)
public abstract class MarkDatabase extends RoomDatabase {

    private static MarkDatabase instance;

    public abstract MarkDao dao();

    public MarkDatabase getInstance(Context context, String nameOfDb) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), MarkDatabase.class, nameOfDb)
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return instance;

    }

}
