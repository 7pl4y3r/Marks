package com.apps.a7pl4y3r.marks;

import android.app.Application;
import android.os.AsyncTask;

import com.apps.a7pl4y3r.marks.room.Discipline;
import com.apps.a7pl4y3r.marks.room.DisciplineDao;
import com.apps.a7pl4y3r.marks.room.DisciplineDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {


    private static final int insertOperation = 1;
    private static final int updateOperation = 2;
    private static final int deleteOperation = 3;


    private DisciplineDao dao;
    private LiveData<List<Discipline>> disciplines;

    public Repository(Application application) {

        DisciplineDatabase db = DisciplineDatabase.getInstance(application);
        dao = db.dao();
        disciplines = dao.getDisciplines();

    }

    public void insert(Discipline discipline) {
        new DatabaseOperation(dao, insertOperation).execute(discipline);
    }

    public void update(Discipline discipline) {
        new DatabaseOperation(dao, updateOperation).execute(discipline);
    }

    public void delete(Discipline discipline) {
        new DatabaseOperation(dao, deleteOperation).execute(discipline);
    }

    public LiveData<List<Discipline>> getDisciplines() {
        return disciplines;
    }


    private static class DatabaseOperation extends AsyncTask<Discipline, Void, Void> {

        private DisciplineDao dao;
        private int operationId;

        private DatabaseOperation(DisciplineDao dao, int operationId) {
            this.dao = dao;
            this.operationId = operationId;
        }

        @Override
        protected Void doInBackground(Discipline... disciplines) {

            switch (operationId) {

                case Repository.insertOperation:
                    dao.insert(disciplines[0]);
                    return null;

                case Repository.updateOperation:
                    dao.update(disciplines[0]);
                    return null;

                case Repository.deleteOperation:
                    dao.delete(disciplines[0]);
                    return null;

                default:
                    return null;

            }


        }


    }

}
