package engine;

import model.*;

import java.util.*;

public class Randomizer {

    public Schedule randomize() {
        return createRound();
    }

    public Schedule createRound() {
        Set<Player> players = new HashSet<Player>(Championship.getInstance().getPlayers());
        Schedule schedule = new Schedule();
        while (players.size()>=4) {
            Player p1 = popRandom(players);
            Player p2 = popRandom(players);
            Player p3 = popRandom(players);
            Player p4 = popRandom(players);
            schedule.add(new Match(new Team(p1, p2), new Team(p3, p4)));
        }
        return schedule;
    }

    private Player popRandom(Set<Player> players) {
        Player player = random(players);
        players.remove(player);
        return player;
    }

    public static <T> T random(Collection<T> coll) {
        int num = (int) (Math.random() * coll.size());
        for(T t: coll) if (--num < 0) return t;
        throw new AssertionError();
    }
}
