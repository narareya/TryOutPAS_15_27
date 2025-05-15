package com.example.tryoutpas_15_27.Premier;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryoutpas_15_27.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PremierLeague extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    TeamAdapter adapter;
    List<Team> teams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premier_league);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        progressBar.setVisibility(View.VISIBLE);

        APIService apiService = ApiClient.getRetrofitInstance().create(APIService.class);
        apiService.getAllTeams("English Premier League").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null){
                    teams = response.body().getTeams();
                    adapter = new TeamAdapter(PremierLeague.this, teams);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(PremierLeague.this, "Data kosong atau error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(PremierLeague.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
