package model;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Championship {

    private Championship() {}

    private static Championship championship;

    public static synchronized Championship getInstance(){
        if (championship == null){
            championship = new Championship();
        }
        return championship;
    }




    public Set<Player> getPlayers() {
        return new TreeSet<>(players);
    }

    private Set<Player> players = new TreeSet<>();

    public void addPlayer(Player player) {
        players.add(player);
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
}
