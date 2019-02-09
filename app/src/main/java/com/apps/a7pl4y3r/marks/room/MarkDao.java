package com.apps.a7pl4y3r.marks.room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MarkDao {

    @Insert
    void insert(Mark mark);

    @Update
    void update(Mark mark);

    @Delete
    void delete(Mark mark);

    @Query("SELECT * FROM `marks.db`")
    LiveData<List<Mark>> getMarks();

}