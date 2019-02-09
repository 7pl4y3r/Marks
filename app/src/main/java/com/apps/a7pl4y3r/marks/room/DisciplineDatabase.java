package com.apps.a7pl4y3r.marks.room;

import android.content.Context;
import android.os.AsyncTask;

import com.apps.a7pl4y3r.marks.Data;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Discipline.class}, version = 1)
public abstract class DisciplineDatabase extends RoomDatabase {

    private static DisciplineDatabase instance;

    public abstract DisciplineDao dao();

    public static DisciplineDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), DisciplineDatabase.class, Data.disciplineDatabaseName)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }

    /*private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDb(instance).execute();
        }
    };


    private static class PopulateDb extends AsyncTask<Void, Void, Void> {

        private DisciplineDao dao;

        public PopulateDb(DisciplineDatabase db) {
            this.dao = db.dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            dao.insert(new Discipline("No disciplines", "press the fab to add one"));
            return null;
        }
    }*/

}
