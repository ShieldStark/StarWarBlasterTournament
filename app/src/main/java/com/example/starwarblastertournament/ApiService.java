package com.example.starwarblastertournament;

import com.example.starwarblastertournament.DataModel.Match;
import com.example.starwarblastertournament.DataModel.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    @GET("b/IKQQ")
    Call<List<Player>> getPlayers();

    @GET("b/JNYL")
    Call<List<Match>> getMatches();
}


