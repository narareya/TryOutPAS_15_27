package com.example.tryoutpas_15_27;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryoutpas_15_27.Premier.APIService;
import com.example.tryoutpas_15_27.Premier.ApiClient;
import com.example.tryoutpas_15_27.Premier.Team;
import com.example.tryoutpas_15_27.Premier.TeamAdapter;
import com.example.tryoutpas_15_27.Premier.TeamResponse;
import com.example.tryoutpas_15_27.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpainFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    TeamAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spain, container, false);
        recyclerView = view.findViewById(R.id.rvTeam);
        progressBar = view.findViewById(R.id.pbLoading);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        APIService apiService = ApiClient.getRetrofitInstance().create(APIService.class);

        apiService.getAllTeams("Spanish La Liga").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                Log.d("TESTING_LOG", "Masuk onResponse");
                if (response.isSuccessful() && response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    List<Team> teams = response.body().getTeams();
                    adapter = new TeamAdapter(getContext(), teams);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.e("MainActivity", "Gagal fetch data: "+t.getMessage());
            }
        });
        return view;
    }
}