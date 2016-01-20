package ui;

import model.Championship;
import model.PlayerRating;
import model.Schedule;

import javax.inject.Named;
import java.util.SortedSet;

@Named
public class View {

    public Schedule getForecast() {
        return Championship.getInstance().getForecast();
    }

    public Schedule getHistory() {
        return Championship.getInstance().getHistory();
    }

    public SortedSet<PlayerRating> getTable(){
        return Championship.getInstance().getRatings();
    }
}
