package com.alexos.myfavoritemovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alexos.myfavoritemovies.model.AppRepository;
import com.alexos.myfavoritemovies.model.Genre;
import com.alexos.myfavoritemovies.model.Movie;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    AppRepository appRepository;
    private LiveData<List<Genre>> genres;
    private LiveData<List<Movie>> genreMovies;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        appRepository = new AppRepository(application);
    }

    public LiveData<List<Genre>> getGenres() {

        genres = appRepository.getGenres();
        return genres;
    }

    public LiveData<List<Movie>> getGenreMovies(int genreId) {

        genreMovies = appRepository.getGenreMovies(genreId);
        return genreMovies;
    }

    public void addNewMovie(Movie movie) {

        appRepository.insertMovie(movie);

    }

    public void updateNewMovie(Movie movie) {

        appRepository.updateMovie(movie);

    }

    public void deleteNewMovie(Movie movie) {

        appRepository.deleteMovie(movie);

    }
}
