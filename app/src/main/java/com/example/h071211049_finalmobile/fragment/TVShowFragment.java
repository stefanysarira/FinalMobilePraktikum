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
import com.example.h071211049_finalmobile.adapter.TVShowAdapter;
import com.example.h071211049_finalmobile.model.TVShow;
import com.example.h071211049_finalmobile.networking.APIConfig;
import com.example.h071211049_finalmobile.networking.APIService;
import com.example.h071211049_finalmobile.networking.DataResponse;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowFragment extends Fragment {
    private RecyclerView rvTVShow;
    ProgressBar progressBar;
    private TVShowAdapter tvShowAdapter;
    private static final String API_KEY = "5c22eab42fad17d6ea013ee8b221e7b4";

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        rvTVShow = view.findViewById(R.id.rvMovie);
        CustomLayoutManager layoutManager = new CustomLayoutManager(getContext(), 2);
        rvTVShow.setLayoutManager(layoutManager);
        tvShowAdapter = new TVShowAdapter();
        rvTVShow.setAdapter(tvShowAdapter);

        APIService apiService = APIConfig.getService();
        Call<DataResponse<List<TVShow>>> call = apiService.getTVShows(API_KEY);
        call.enqueue(new Callback<DataResponse<List<TVShow>>>() {
            @Override
            public void onResponse(Call<DataResponse<List<TVShow>>> call, Response<DataResponse<List<TVShow>>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    List<TVShow> tvShowList = response.body().getData();
                    tvShowAdapter.setTVShows(tvShowList);
                } else {
                    Toast.makeText(getActivity(), "Error: Berhasil dengan internet tetapi gagal karna " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataResponse<List<TVShow>>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
