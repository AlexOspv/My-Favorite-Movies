package com.alexos.myfavoritemovies.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "genres_table")
public class Genre extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "genre_name")
    private String genreName;

    @Ignore
    public Genre() {
    }

    public Genre(int id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
        notifyPropertyChanged(BR.genreName);
    }

    @Override
    public String toString() {
        return this.genreName;
    }
}
