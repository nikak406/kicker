package model;

import engine.Randomizer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@XmlRootElement
public class Championship {

    public Championship() {}

    public int n() {
        return this.getHistory().size();
    }

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
            championship.setPlayers(new HashSet<Player>());
            championship.setMatches(new Schedule());
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

    @XmlTransient
    public void setMatches(Schedule matches) {
        this.matches = matches;
    }

    private Schedule matches = new Schedule();

    public Schedule getForecast() {
        Schedule forecast = new Schedule();
        for (Match match : matches) {
            if (match.getScore() == null) {
                forecast.add(match);
            }
        }
        return forecast;
    }

    public Schedule getHistory(){
        Schedule history = new Schedule();
        for (Match match : matches) {
            if (match.getScore() != null) {
                history.add(match);
            }
        }
        return history;
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
        ratings.sort(new RatingComparator());
        return ratings;
    }
}
