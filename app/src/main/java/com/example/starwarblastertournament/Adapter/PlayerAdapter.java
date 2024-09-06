package com.example.starwarblastertournament.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starwarblastertournament.DataModel.Player;
import com.example.starwarblastertournament.MatchesActivity;
import com.example.starwarblastertournament.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> playerList;
    private Context context;
    private HashMap<Integer, Integer> playerWins;
    private OnPlayerClickListener onPlayerClickListener;

    public PlayerAdapter(List<Player> playerList, Context context) {
        Log.d("PlayerAdapter", "Size of player list: " + playerList.size());
        this.playerList = playerList;
        this.context = context;
        this.playerWins = new HashMap<>();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_row, parent, false);
        return new PlayerViewHolder(view);
    }
    public void updatePlayerWins(HashMap<Integer, Integer> playerWins) {
        this.playerWins = playerWins;
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                int wins1 = playerWins.getOrDefault(p1.getId(), 0);
                int wins2 = playerWins.getOrDefault(p2.getId(), 0);
                return Integer.compare(wins2, wins1);
            }
        });

        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.nameTextView.setText(player.getName());
        int wins = playerWins.getOrDefault(player.getId(), 0);
        holder.pointsTextView.setText(String.valueOf(wins));
        if (player.getUrl() != null && !player.getUrl().isEmpty()) {
            Log.d("!!!PlayerAdapter", "Loading URL: " + player.getUrl());
            Glide.with(context)
                    .load(player.getUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.imageView);
        } else {
            Log.e("PlayerAdapter", "Image URL is null or empty for player: " + player.getName());
        }

        // Set onClick listener to navigate to MatchesActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MatchesActivity.class);
            intent.putExtra("playerName", player.getName());
            intent.putExtra("playerId",player.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }
    public void setOnPlayerClickListener(OnPlayerClickListener listener) {
        this.onPlayerClickListener = listener;
    }

    public interface OnPlayerClickListener {
        void onPlayerClick(Player player);
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, pointsTextView;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.playerImage);
            nameTextView = itemView.findViewById(R.id.playerName);
            pointsTextView = itemView.findViewById(R.id.playerPoints);
        }
    }
}
