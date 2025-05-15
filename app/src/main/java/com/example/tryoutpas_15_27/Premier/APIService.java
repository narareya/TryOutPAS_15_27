package com.example.tryoutpas_15_27.Premier;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("search_all_teams.php")
    Call<TeamResponse> getAllTeams(@Query("l") String league);
}
