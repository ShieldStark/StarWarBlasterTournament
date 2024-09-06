package com.example.starwarblastertournament.HttpRequest;

import android.content.Context;
import android.util.Log;

import com.example.starwarblastertournament.ApiService;
import com.example.starwarblastertournament.DataModel.Match;
import com.example.starwarblastertournament.DataModel.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiManager {
    private ApiService apiService;
    //List<Player> responsePlayer;
    ResponseListener responsePlayer;

    public ApiManager(Context context) {
        responsePlayer=(ResponseListener)context;
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        apiService = retrofit.create(ApiService.class);
    }

    public void fetchPlayers() {

        Log.d("!!!!!ApiManager","fetec");
        Call<List<Player>> call = apiService.getPlayers();
        Log.d("!!!!!ApiManager","feteccall");
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful() && response != null) {
                    Log.d("!!!!!ApiManager",response.toString());
                    responsePlayer.PlayerListResponse(response.body());

                }
                Log.d("!!!!!ApiManager","feteccall..");
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.d("!!!!!ApiManager","ERRRR");

            }
        });
    }

    public void fetchMatches() {
        Call<List<Match>> call = apiService.getMatches();
        call.enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Match> matches = response.body();
                    responsePlayer.MatchListResponse(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                Log.d("!!!!!ApiManager","ERRRR");
            }
        });
    }
    public interface ResponseListener{
        void PlayerListResponse(List<Player> playerList);
        void MatchListResponse(List<Match> matchList);
    }
}

