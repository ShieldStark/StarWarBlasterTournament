package com.example.starwarblastertournament.HttpRequest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.starwarblastertournament.DataModel.Player;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FetchPlayerDataTask extends AsyncTask<String, Void, List<Player>> {

    PlayerResponseListener playerResponseListener;
    public FetchPlayerDataTask(Context context){
        this.playerResponseListener=(PlayerResponseListener) context;
    }
    @Override
    protected List<Player> doInBackground(String... urls) {
        List<Player> playerList = new ArrayList<>();
        HashMap<String, Player> playerMap = new HashMap<>();

        try {
            URL playerUrl = new URL(urls[0]);
            HttpURLConnection playerConnection = (HttpURLConnection) playerUrl.openConnection();
            BufferedReader playerReader = new BufferedReader(new InputStreamReader(playerConnection.getInputStream()));
            StringBuilder playerBuilder = new StringBuilder();
            String playerLine;
            while ((playerLine = playerReader.readLine()) != null) {
                playerBuilder.append(playerLine);
            }
            JSONArray playerArray = new JSONArray(playerBuilder.toString());
            for (int i = 0; i < playerArray.length(); i++) {
                JSONObject playerObject = playerArray.getJSONObject(i);
                int id = playerObject.getInt("id");
                String name = playerObject.getString("name");
                String icon = playerObject.getString("icon");
                playerMap.put(name, new Player(id, name, icon));
            }
            playerList.addAll(playerMap.values());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return playerList;
    }

    @Override
    protected void onPostExecute(List<Player> result) {
        Log.d("!!!!!FetchData",""+result.size());
        playerResponseListener.PlayerResponse(result);
    }
    public interface PlayerResponseListener{
        void PlayerResponse(List<Player> response);
    }
}
