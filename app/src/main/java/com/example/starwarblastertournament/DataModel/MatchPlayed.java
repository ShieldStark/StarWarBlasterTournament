package com.example.starwarblastertournament.DataModel;

import java.io.Serializable;

public class MatchPlayed implements Serializable {
    int id;
    int score1;
    int score2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public MatchPlayed(int score1, int id, int score2) {
        this.score1 = score1;
        this.id = id;
        this.score2 = score2;
    }
}
