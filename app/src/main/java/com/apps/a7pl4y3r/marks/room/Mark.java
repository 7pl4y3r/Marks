package com.apps.a7pl4y3r.marks.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "marks.db")
public class Mark {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String date;

    public Mark(String title, String date) {
        this.title = title;
        this.date = date;
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

    public String getDate() {
        return date;
    }

}
