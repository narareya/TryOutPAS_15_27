package com.example.tryoutpas_15_27;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryoutpas_15_27.Premier.APIService;
import com.example.tryoutpas_15_27.Premier.ApiClient;
import com.example.tryoutpas_15_27.Premier.Team;
import com.example.tryoutpas_15_27.Premier.TeamAdapter;
import com.example.tryoutpas_15_27.Premier.TeamResponse;
import com.example.tryoutpas_15_27.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PremiereFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    TeamAdapter adapter;
    List<Team> teams = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_premiere, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar.setVisibility(View.VISIBLE);

        APIService apiService = ApiClient.getRetrofitInstance().create(APIService.class);
        apiService.getAllTeams("English Premier League").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    teams = response.body().getTeams();
                    adapter = new TeamAdapter(getContext(), teams);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Data kosong atau error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        return view;
    }
}