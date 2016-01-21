package model;

import engine.Randomizer;

import java.util.*;
import java.util.stream.Collectors;

public class Championship {

    private Championship() {}

    private static Championship championship;

    public static synchronized Championship getInstance(){
        if (championship == null){
            championship = new Championship();
            championship.players.add(new Player("Грыша_Борець"));
            championship.players.add(new Player("Миша_Боксер"));
            championship.players.add(new Player("Коля_Уркаган"));
            championship.players.add(new Player("Сережа_Молодец"));
            championship.setMatches(new Randomizer().randomize());
        }
        return championship;
    }

    public Set<Player> getPlayers() {
        return new HashSet<>(players);
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    private Set<Player> players = new HashSet<>();

    public void addPlayer(Player player) {
        players.add(player);
    }



    public void setMatches(Schedule matches) {
        this.matches = matches;
    }

    private Schedule matches = new Schedule();

    public Schedule getForecast(){
        return matches.stream().filter(match -> match.getScore() == null).collect(Collectors.toCollection(Schedule::new));
    }

    public Schedule getHistory(){
        return matches.stream().filter(match -> match.getScore() != null).collect(Collectors.toCollection(Schedule::new));
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public SortedSet<PlayerRating> getRatings(){
        SortedSet<PlayerRating> ratings = new TreeSet<>((p1, p2) -> p1.getScore() - p2.getScore());
        for (Player player : players) {
            ratings.add(new PlayerRating(player, getHistory().playing(player)));
        }
        return ratings;
    }
}
