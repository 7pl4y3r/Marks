package com.apps.a7pl4y3r.marks.room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public abstract class MarkDao {

    private String databaseName;

    public MarkDao(String databaseName) {
        this.databaseName = databaseName;
    }

    @Insert
    public abstract void insert(Mark mark);

    @Update
    public abstract void update(Mark mark);

    @Delete
    public abstract void delete(Mark mark);

    @Query("SELECT * FROM `marks.db` ORDER BY title ASC")
    public abstract LiveData<List<Mark>> getMarks();

}