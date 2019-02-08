package com.apps.a7pl4y3r.marks.room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DisciplineDao {

    @Insert
    void insert(Discipline discipline);

    @Update
    void update(Discipline discipline);

    @Delete
    void delete(Discipline discipline);

    @Query("SELECT * FROM `discipline.db` ORDER BY title ASC")
    LiveData<List<Discipline>> getDisciplines();

}