package com.apps.a7pl4y3r.marks;

import android.app.Application;

import com.apps.a7pl4y3r.marks.room.Discipline;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {


    private Repository repository;
    private LiveData<List<Discipline>> disciplines;


    public ViewModel(Application application) {
        super(application);

        repository = new Repository(application);
        disciplines = repository.getDisciplines();

    }


    public void insert(Discipline discipline) {
        repository.insert(discipline);
    }

    public void update(Discipline discipline) {
        repository.update(discipline);
    }

    public void delete(Discipline discipline) {
        repository.delete(discipline);
    }


    public LiveData<List<Discipline>> getDisciplines() {
        return disciplines;
    }

}
