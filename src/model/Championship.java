package model;

import engine.Randomizer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;
import java.util.stream.Collectors;

@XmlRootElement
public class Championship {

    public Championship() {}

    public static void setInstance(Championship championship) {
        Championship.championship = championship;
        if (championship.getForecast().isEmpty() &&
                championship.getHistory().isEmpty()){
                    championship.setMatches(new Randomizer().randomize());
        }
    }

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


    public Match findMatch(String string) {
        String[] array = string.split("\\|");
        Team team1, team2;
        team1 = findTeam(array[0]);
        team2 = findTeam(array[2]);
        for (Match match : Championship.getInstance().getForecast()){
            if (match.getTeam1().equals(team1) && match.getTeam2().equals(team2)){
                return match;
            }
        }
        return null;
    }


    public Player findPlayer(String name){
        for (Player player : players) {
            if (player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }


    public Team findTeam(String string){
        String[] array = string.trim().split(" ");
        String name1 = array[0];
        String name2 = array[1];
        Player player1 = findPlayer(name1);
        Player player2 = findPlayer(name2);
        return new Team(player1, player2);
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


    @XmlTransient
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

    public void setForecast(Schedule matches) {
        this.matches.addAll(matches);
    }

    public void setHistory(Schedule matches) {
        this.matches.addAll(matches);
    }


    public void addMatch(Match match) {
        matches.add(match);
    }

    @XmlTransient
    public List<PlayerRating> getRatings(){
        List<PlayerRating> ratings = new ArrayList<>();
        for (Player player : players) {
            ratings.add(new PlayerRating(player, getHistory().playing(player)));
        }
        ratings.sort(((p1, p2) -> {
            if (p2.getCoef() != p1.getCoef()){
                return p2.getCoef() - p1.getCoef();
            } else {
                return (int) Math.signum(((float)p2.getDifference()/(float)p2.getGamesPlayed())
                        - ((float)p1.getDifference()/(float)p2.getGamesPlayed()));
            }
        }));
        return ratings;
    }
}
