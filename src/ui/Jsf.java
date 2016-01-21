package ui;

import model.Championship;
import model.PlayerRating;
import model.Schedule;

import javax.inject.Named;
import java.util.SortedSet;

@Named
public class Jsf {

    public Schedule getForecast() {
        return Championship.getInstance().getForecast();
    }

    public Schedule getHistory() {
        return Championship.getInstance().getHistory();
    }

    public SortedSet<PlayerRating> getRatings(){
        return Championship.getInstance().getRatings();
    }
}
