package com.example.starwarblastertournament.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarblastertournament.DataModel.Match;
import com.example.starwarblastertournament.DataModel.Player;
import com.example.starwarblastertournament.DataModel.PlayerDetail;
import com.example.starwarblastertournament.R;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {
    private List<Match> matchList;
    private String playerName;

    public MatchAdapter(List<Match> matchList, String playerName) {
        this.matchList = matchList;
        this.playerName = playerName;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_row, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matchList.get(position);
        holder.player1TextView.setText(match.getPlayer1().getName());
        holder.player2TextView.setText(match.getPlayer2().getName());
        holder.score1TextView.setText(String.valueOf(match.getPlayer1().getScore()));
        holder.score2TextView.setText(String.valueOf(match.getPlayer2().getScore()));
        PlayerDetail selectedPlayer;
        PlayerDetail opponent;
        int selectedPlayerScore;
        int opponentScore;

        if (match.getPlayer1().getName().equals(playerName)) {
            selectedPlayer = match.getPlayer1();
            opponent = match.getPlayer2();
            selectedPlayerScore = match.getPlayer1().getScore();
            opponentScore = match.getPlayer2().getScore();
        } else {
            selectedPlayer = match.getPlayer2();
            opponent = match.getPlayer1();
            selectedPlayerScore = match.getPlayer2().getScore();
            opponentScore = match.getPlayer1().getScore();
        }
        if (selectedPlayerScore > opponentScore) {
            holder.itemView.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else if (selectedPlayerScore < opponentScore) {
            holder.itemView.setBackgroundColor(Color.parseColor("#F44336"));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView player1TextView, player2TextView, score1TextView, score2TextView;

        public MatchViewHolder(View itemView) {
            super(itemView);
            player1TextView = itemView.findViewById(R.id.player1);
            player2TextView = itemView.findViewById(R.id.player2);
            score1TextView = itemView.findViewById(R.id.score1);
            score2TextView = itemView.findViewById(R.id.score2);
        }
    }
}

