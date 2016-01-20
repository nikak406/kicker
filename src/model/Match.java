package model;

import java.util.Set;
import java.util.HashSet;

public class Match {

    private static int count = 0;

    public Team getTeam1() {
        return team1;
    }

    public int getNumber() {
        return number;
    }

    public Score getScore() {
        return score;
    }

    public Team getTeam2() {
        return team2;
    }

    private Team team1;
    private Team team2;

    private int number;

    public void setScore(Score score) {
        this.score = score;
    }

    private Score score;

    public Match(Team team1, Team team2) {
        assert team1.canPlay(team2);
        this.team1 = team1;
        this.team2 = team2;
        number = count++;
    }

    @Override
    public String toString() {
        return "" + team1 + " | " + score  + " | " + team2;
    }

    public Set<Team> getTeams() {
        Set<Team> set = new HashSet<>();
        set.add(team1);
        set.add(team2);
        return set;
    }

    public boolean playerWined(Player player){
        int diff = score.score1 - score.score2;
        return (diff > 0 && team1.contains(player)) ||
                (diff < 0) && team2.contains(player);
    }
}
