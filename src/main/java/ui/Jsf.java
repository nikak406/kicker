package ui;

import model.*;

import javax.inject.Named;
import java.util.Comparator;
import java.util.List;

@Named
public class Jsf {

    private String score;

    public Schedule getForecast() {
        return Championship.getInstance().getForecast();
    }

    public Schedule getHistory() {
        Schedule list = Championship.getInstance().getHistory();
        Comparator<Match> comparator = new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                return o2.getN() - o1.getN();
            }
        };
        list.sort(comparator);
        return list;
    }

    public List<PlayerRating> getRatings() {
        return Championship.getInstance().getRatings();
    }

    public String getMatch() {
        if (match != null){
            return match.toString();
        } else {
            return "";
        }
    }

    public String getScore() {
        return score;
    }

    private Match match;

    public void setMatch(String match) {
        this.match = Championship.getInstance().findMatch(match);
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void sendScore(){
        if (match != null){
            match.setScore(new Score(score));
        }
    }
}
