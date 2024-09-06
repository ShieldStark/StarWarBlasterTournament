package com.example.starwarblastertournament;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarblastertournament.Adapter.MatchAdapter;
import com.example.starwarblastertournament.DataModel.Match;
import com.example.starwarblastertournament.DataModel.MatchPlayed;
import com.example.starwarblastertournament.DataModel.Player;
import com.example.starwarblastertournament.DataModel.PlayerDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {

    private RecyclerView matchesRecyclerView;
    private MatchAdapter matchAdapter;
    private List<Match> matchList;
    private String playerName;
    private int playerId;
    View mBack;
    private HashMap<Integer, List<MatchPlayed>> matchMap=new HashMap<>();
    private HashMap<Integer, String> playerMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        matchMap = MainActivity.matchMap;
        playerMap = MainActivity.playerMap;
        matchList=new ArrayList<>();
        if (matchMap == null) {
            matchMap = new HashMap<>();
        }

        if (playerMap == null) {
            playerMap = new HashMap<>();
        }
        //Log.d("!!!",""+matchMap.size());
        mBack=findViewById(R.id.action_bar_back_icon);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        playerName = intent.getStringExtra("playerName");
        playerId=intent.getIntExtra("playerId",0);
        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        playerNameTextView.setText("Matches for " + playerName);
        matchesRecyclerView = findViewById(R.id.matchesRecyclerView);
        matchesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayMatchesForPlayer(playerId);
    }

    private List<Match> getMatchesForPlayer(String playerName) {
        List<Match> matches = new ArrayList<>();

        return matches;
    }
    private void displayMatchesForPlayer(int playerId) {
        if (matchMap!=null) {
            List<MatchPlayed> matches = matchMap.get(playerId);
            Collections.reverse(matches);
            String playerName = playerMap.get(playerId);

            if (matches != null) {
                Log.d("MatchesActivity", "Matches for " + playerName + ":");
                for (MatchPlayed match : matches) {
                    matchList.add(new Match(new PlayerDetail(playerName,match.getScore1()),new PlayerDetail(playerMap.get(match.getId()),match.getScore2())));
                    Log.d("MatchesActivity", "Opponent ID: " + match.getId() + ", Score: " + match.getScore1() + ", Opponent Score: " + match.getScore2());
                }
                matchAdapter = new MatchAdapter(matchList, playerName);
                matchesRecyclerView.setAdapter(matchAdapter);
            } else {
                Log.d("MatchesActivity", "No matches found for player ID: " + playerId);
            }
        }
    }
}
