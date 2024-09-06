package com.example.starwarblastertournament.DataModel;

import com.google.gson.annotations.SerializedName;

public class Player {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("icon")
    private String icon;

    public Player(int id,String name,String url) {
        this.name = name;
    }

    public String getName() { return name; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return icon;
    }

    public void setUrl(String url) {
        this.icon = url;
    }
}

