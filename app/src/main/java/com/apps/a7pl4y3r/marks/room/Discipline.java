package com.apps.a7pl4y3r.marks.room;

import com.apps.a7pl4y3r.marks.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Data.disciplineDatabaseName)
public class Discipline {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String marksCount;

    public Discipline(String title, String marksCount) {
        this.title = title;
        this.marksCount = marksCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMarksCount() {
        return marksCount;
    }

}
