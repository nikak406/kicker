package engine;

import model.*;

import java.util.*;

public class Randomizer {

    public Schedule randomize() {
        /*int n = Championship.getInstance().getPlayers().size();
        Schedule schedule;
        int count = 0;
        Schedule min = createSchedule(allPossibleTeams());
        while (min.size() > n-1 && count < 10000) {
            count++;
            schedule = createSchedule(allPossibleTeams());
            if (min.size() > schedule.size() && isFull(schedule)) {
                min = schedule;
            }
        }
        return min;*/
        return createRound();
    }

    public Schedule createRound() {
        Set<Player> players = new HashSet(Championship.getInstance().getPlayers());
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

    private Schedule createSchedule(Set<Team> teams) {
        Schedule schedule = new Schedule();
        if (teams.isEmpty()) {
            return schedule;
        }
        Team team1 = random(teams);
        teams.remove(team1);
        Match match;
        Team team2 = getPartner(team1, teams);
        if (team2 != null){
            teams.remove(team2);
        } else {
            team2 = getPartner(team1, allPossibleTeams());
        }
        match = new Match(team1, team2);
        schedule.add(match);
        schedule.addAll(createSchedule(teams));
        return schedule;
    }

    private Team getPartner(Team team, Set<Team> teamsSet) {
        List<Team> teams = new ArrayList<>(teamsSet);
        Collections.shuffle(team);
        for (Team cteam : teams) {
            if (cteam.canPlay(team)) {
                return cteam;
            }
        }
        return null;
    }

    private boolean isFull(Schedule schedule){
        Set<Player> players = Championship.getInstance().getPlayers();
        for (Player player : players) {
            if (!playerIsFull(player, schedule)){
                return false;
            }
        }
        return true;
    }

    private boolean playerIsFull(Player player, Schedule schedule) {
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

    private Set<Team> allPossibleTeams() {
        Set<Team> apt = new HashSet<>();
        Set<Player> players = Championship.getInstance().getPlayers();
        for (Player attacker : players) {
            for (Player defender : players) {
                if (attacker != defender) {
                    Team team = new Team(attacker, defender);
                    apt.add(team);
                }
            }
        }
        return apt;
    }

    public static <T> T random(Collection<T> coll) {
        int num = (int) (Math.random() * coll.size());
        for(T t: coll) if (--num < 0) return t;
        throw new AssertionError();
    }


}
