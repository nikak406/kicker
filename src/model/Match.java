package model;

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
        Schedule.forecast.remove(this);
        Schedule.history.add(this);
    }

    private Score score;

    public Match(Team team1, Team team2) {
        checkPlayers(team1, team2);
        this.team1 = team1;
        this.team2 = team2;
        number = count++;
        Schedule.forecast.add(this);
    }

    private void checkPlayers(Team team1, Team team2) {
        Player a1 = team1.getAttacker();
        Player d1 = team1.getDefender();
        Player a2 = team2.getAttacker();
        Player d2 = team2.getDefender();
        assert (a1 != a2);
        assert (a1 != d2);
        assert (d1 != a2);
        assert (d1 != d2);
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
