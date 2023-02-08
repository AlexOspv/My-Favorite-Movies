package com.alexos.myfavoritemovies;

import android.os.Bundle;

import com.alexos.myfavoritemovies.databinding.ActivityMainBinding;
import com.alexos.myfavoritemovies.model.Genre;
import com.alexos.myfavoritemovies.model.Movie;
import com.alexos.myfavoritemovies.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers clickHandlers;
    private Genre selectedGenre;
    private ArrayList<Genre> genreArrayList;
    private ArrayList<Movie> movieArrayList;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        clickHandlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(clickHandlers);

        mainActivityViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {

                genreArrayList = (ArrayList<Genre>) genres;

                for (Genre genre : genres) {

                    Log.d("MyTAG", genre.getGenreName());

                }

                showInSpinner();

            }
        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void showInSpinner() {

        ArrayAdapter<Genre> genreArrayAdapter = new ArrayAdapter<Genre>(this,
                R.layout.spinner_item, genreArrayList);
        genreArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(genreArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MainActivityClickHandlers {

        public void onFabClicked(View view) {

            Toast.makeText(MainActivity.this, "Button is clicked", Toast.LENGTH_SHORT).show();

        }

        public void onSelectedItem(AdapterView<?> parent, View view, int position, long id) {

            selectedGenre = (Genre) parent.getItemAtPosition(position);
            String message = "id is " + selectedGenre.getId() +
                    "\n name is " + selectedGenre.getGenreName();
            Toast.makeText(parent.getContext(), message, Toast.LENGTH_SHORT).show();

            loadGenreMoviesInArrayList(selectedGenre.getId());
        }

    }

    private void loadGenreMoviesInArrayList(int genreId) {

        mainActivityViewModel.getGenreMovies(genreId).observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {

                        movieArrayList = (ArrayList<Movie>) movies;
                        loadRecyclerView();

                    }
                });
    }

    private void loadRecyclerView() {

        recyclerView = activityMainBinding.secondaryLayout.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        movieAdapter = new MovieAdapter();
        movieAdapter.setMovieArrayList(movieArrayList);
        recyclerView.setAdapter(movieAdapter);


    }


}