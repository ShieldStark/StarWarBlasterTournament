package com.example.starwarblastertournament.DataModel;

public class PlayerDetail {
        private int id;
        private String name;
        private int score;

        public PlayerDetail(int id, int score, String name) {
                this.id = id;
                this.score = score;
                this.name = name;
        }

        public PlayerDetail(String name, int score) {
                this.name = name;
                this.score = score;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String id) { this.name = name; }
        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }

}
