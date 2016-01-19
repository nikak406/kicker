package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        checkPlayers(team1, team2);
        this.team1 = team1;
        this.team2 = team2;
        number = count++;
        Championship.getInstance().addMatch(this);
    }

    private void checkPlayers(Team team1, Team team2) {
        List<Player> copy = new ArrayList<>(team1);
        copy.retainAll(team2);
        assert (copy.isEmpty());
    }

    @Override
    public String toString() {
        return "" + team1 + score + team2;
    }

    public Set<Team> getTeams() {
        Set<Team> set = new TreeSet<>();
        set.add(team1);
        set.add(team2);
        return set;
    }
}
