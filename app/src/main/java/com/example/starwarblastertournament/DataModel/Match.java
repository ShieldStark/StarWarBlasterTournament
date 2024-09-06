package com.example.starwarblastertournament.DataModel;

public class Match {
    int match;
    private PlayerDetail player1;
    private PlayerDetail player2;

    public Match(PlayerDetail player1, PlayerDetail player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public void setPlayer1(PlayerDetail player1) {
        this.player1 = player1;
    }

    public void setPlayer2(PlayerDetail player2) {
        this.player2 = player2;
    }

    public PlayerDetail getPlayer1() { return player1; }
    public PlayerDetail getPlayer2() { return player2; }

}
