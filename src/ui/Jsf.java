package ui;

import model.*;

import javax.inject.Named;
import java.util.SortedSet;

@Named
public class Jsf {

    private String score;

    public Schedule getForecast() {
        return Championship.getInstance().getForecast();
    }

    public Schedule getHistory() {
        return Championship.getInstance().getHistory();
    }

    public SortedSet<PlayerRating> getRatings(){
        return Championship.getInstance().getRatings();
    }

    public Match getMatch() {
        return match;
    }

    public String getScore() {
        return score;
    }

    private Match match;

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void sendScore(){
        match.setScore(new Score(score));
    }
}
