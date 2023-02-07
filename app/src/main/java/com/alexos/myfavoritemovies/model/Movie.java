package com.alexos.myfavoritemovies.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies_table", foreignKeys = @ForeignKey(entity = Genre.class,
        parentColumns = "id", childColumns = "genre_id", onDelete = ForeignKey.CASCADE))
public class Movie extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    private int movieId;
    @ColumnInfo(name = "movie_name")
    private String movieName;
    @ColumnInfo(name = "movie_description")
    private String movieDescription;
    @ColumnInfo(name = "genre_id")
    private int genreId;

    @Ignore
    public Movie() {
    }

    public Movie(int movieId, String movieName, String movieDescription, int genreId) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.genreId = genreId;
    }

    @Bindable
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
        notifyPropertyChanged(BR.movieId);
    }

    @Bindable
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
        notifyPropertyChanged(BR.movieName);
    }

    @Bindable
    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
        notifyPropertyChanged(BR.movieDescription);
    }

    @Bindable
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
        notifyPropertyChanged(BR.genreId);
    }
}
