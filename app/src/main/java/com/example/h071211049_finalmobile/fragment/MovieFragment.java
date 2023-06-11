package com.example.h071211049_finalmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211049_finalmobile.CustomLayoutManager;
import com.example.h071211049_finalmobile.R;
import com.example.h071211049_finalmobile.adapter.MovieAdapter;
import com.example.h071211049_finalmobile.model.Movie;
import com.example.h071211049_finalmobile.networking.APIConfig;
import com.example.h071211049_finalmobile.networking.APIService;
import com.example.h071211049_finalmobile.networking.DataResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {
    RecyclerView rvMovie;
    ProgressBar progressBar;
    private MovieAdapter movieAdapter;
    private static final String API_KEY = "5c22eab42fad17d6ea013ee8b221e7b4";

    public MovieFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        rvMovie = view.findViewById(R.id.rvMovie);
        CustomLayoutManager layoutManager = new CustomLayoutManager(getContext(), 2);
        rvMovie.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter();
        rvMovie.setAdapter(movieAdapter);
        APIService apiService = APIConfig.getService();
        Call<DataResponse<List<Movie>>> call = apiService.getMovies(API_KEY);
        call.enqueue(new Callback<DataResponse<List<Movie>>>() {
            @Override
            public void onResponse(Call<DataResponse<List<Movie>>> call, Response<DataResponse<List<Movie>>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    List<Movie> movieList = response.body().getData();
                    movieAdapter.setMovies(movieList);
                } else {
                    Toast.makeText(getActivity(), "Error: Berhasil dengan internet tetapi gagal karna " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DataResponse<List<Movie>>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}