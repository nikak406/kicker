package engine;

import model.Championship;
import model.Player;
import model.Schedule;
import model.Team;

import java.util.Collection;
import java.util.Set;

public class Randomizer {

    private final Set<Player> players;
    private Schedule schedule = new Schedule();

    public Randomizer() {
        this.players = Championship.getInstance().getPlayers();
    }

    public Schedule randomize() {
        Set<Team> apt = allPossibleTeams();
        //TODO
        return null;

    }

    private boolean scheduleIsFull(){
        Set<Player> players = Championship.getInstance().getPlayers();
        for (Player player : players) {
            if (!playerIsFull(player)){
                return false;
            }
        }
        return true;
    }

    private boolean playerIsFull(Player player) {
        Set<Team> playerTeams = schedule.getTeams(player);
        Set<Player> players = Championship.getInstance().getPlayers();
        players.remove(player);
        for (Team team : playerTeams) {
            Player anotherPlayer = team.getAnotherPlayer(player);
            if (players.contains(anotherPlayer)) {
                players.remove(anotherPlayer);
            }
        }
        return players.isEmpty();
    }

    private Set<Team> allPossibleTeams(){
        return null;
        //TODO
    }

    public static <T> T random(Collection<T> coll) {
        int num = (int) (Math.random() * coll.size());
        for(T t: coll) if (--num < 0) return t;
        throw new AssertionError();
    }


}
