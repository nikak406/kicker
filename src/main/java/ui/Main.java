package ui;

import engine.Randomizer;
import model.Championship;
import model.Player;

import java.util.Set;
import java.util.HashSet;

public class Main {

    public static Set<Player> createPlayers(int size) {
        Set<Player> result = new HashSet<Player>();
        for (int i = 0; i < size; i++) {
            result.add(new Player("player" + (i + 1)));
        }
        return result;
    }

    public static void main(String[] args) {
        Championship championship = Championship.getInstance();
        championship.setPlayers(createPlayers(16));
        Randomizer r = new Randomizer();
        championship.setMatches(r.randomize());
        System.out.println(championship.getForecast());
    }
}
