package com.example.sherisesinyeelam.java4kids.TheWorldLeaderBoard;

public class SpaceCraft implements Comparable{

    int id;
    String username;
    int level, score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // sorting the user according to their score.
    @Override
    public int compareTo(Object o) {
        int compareScore = ((SpaceCraft) o).getScore();
        return compareScore - this.score;
    }
}
