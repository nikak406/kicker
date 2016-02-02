package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Set;
import java.util.HashSet;

@XmlRootElement
public class Match {

    public Match() {
    }

    public Team getTeam1() {
        return team1;
    }

    public Score getScore() {
        return score;
    }

    public Team getTeam2() {
        return team2;
    }

    private Team team1;
    private Team team2;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    private int n = -1;

    public void setScore(Score score) {
        this.score = score;
        if (this.getN() == -1){
            this.setN(Championship.getInstance().n());
        }
    }

    private Score score;

    public Match(Team team1, Team team2) {
        assert team1.canPlay(team2);
        this.team1 = team1;
        this.team2 = team2;
    }

    @Override

    public String toString() {
        String scoreText = (score == null) ? "" : score.toString();
        return "" + team1 + " | " + scoreText + " | " + team2;
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
