package com.example.starwarblastertournament;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarblastertournament.Adapter.PlayerAdapter;
import com.example.starwarblastertournament.DataModel.Match;
import com.example.starwarblastertournament.DataModel.MatchPlayed;
import com.example.starwarblastertournament.DataModel.Player;
import com.example.starwarblastertournament.HttpRequest.ApiManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ApiManager.ResponseListener {

    private RecyclerView pointsRecyclerView;
    private PlayerAdapter playerAdapter;
    private List<Player> playerList;
    View mBack;
    private ApiManager apiManager;
    public static HashMap<Integer,List<MatchPlayed>> matchMap=new HashMap<>();
    public static HashMap<Integer,String> playerMap=new HashMap<>();
    public HashMap<Integer, Integer> playerWins = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBack=findViewById(R.id.action_bar_back_icon);
        mBack.setVisibility(View.INVISIBLE);
        pointsRecyclerView = findViewById(R.id.pointsRecyclerView);
        pointsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        playerList = new ArrayList<>();
        //new FetchPlayerDataTask(this).execute("https://www.jsonkeeper.com/b/JNYL");
       apiManager = new ApiManager(this);
        apiManager.fetchMatches();
        apiManager.fetchPlayers();

    }
    @Override
    public void PlayerListResponse(List<Player> playerList) {
        Log.d("!!!!Main","Main"+playerList.size());
        for (Player it:playerList){
            playerMap.put(it.getId(),it.getName());
            playerWins.put(it.getId(), 0);
        }
        playerAdapter = new PlayerAdapter(playerList,this);
        pointsRecyclerView.setAdapter(playerAdapter);
        playerAdapter.notifyDataSetChanged();
    }

    @Override
    public void MatchListResponse(List<Match> matchList) {
        Log.d("!!!Main","Match"+matchList.size());
        for (Match it:matchList){
            int player1Id = it.getPlayer1().getId();
            int player2Id = it.getPlayer2().getId();
            int player1Score = it.getPlayer1().getScore();
            int player2Score = it.getPlayer2().getScore();
            matchMap.computeIfAbsent(player1Id, k -> new ArrayList<>())
                    .add(new MatchPlayed(player1Score, player2Id, player2Score));
            matchMap.computeIfAbsent(player2Id, k -> new ArrayList<>())
                    .add(new MatchPlayed(player2Score, player1Id, player1Score));
            if (player1Score > player2Score) {
                playerWins.put(player1Id, playerWins.get(player1Id) + 3);
            } else if (player2Score > player1Score) {
                playerWins.put(player2Id, playerWins.get(player2Id) + 3);
            }else {
                playerWins.put(player1Id, playerWins.get(player1Id) + 1);
                playerWins.put(player2Id, playerWins.get(player2Id) + 1);
            }
        }
        Log.d("!!!!","HashMap"+matchMap.size()+"size 0"+playerWins.get(1));
        updatePlayerAdapter();
    }
    private void updatePlayerAdapter() {
        if (playerAdapter != null) {
            // Update the adapter data with the number of wins
            playerAdapter.updatePlayerWins(playerWins);
            playerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        matchMap.clear();
        playerMap.clear();
        playerWins.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        matchMap.clear();
        playerMap.clear();
        playerWins.clear();
    }
}
