package model;

import java.util.*;

public class Schedule extends ArrayList<Match> {

    public Schedule() {
    }

    public Schedule(List<Match> list){
        super(list);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Schedule:\n");
        for (Match match : this) {
            builder.append(match).append("\n");
        }
        return builder.toString();
    }

    public Schedule head(int i){
        if (this.size() <= i) return this;
        return new Schedule(this.subList(0, i));
    }

    public Schedule head(){
        Schedule result = createHead();
        filter(result);
        return result;
    }

    private void filter(Schedule result) {
        for (int i = 0; i < result.size() ; i++) {
            Match match = result.get(i);
            result.remove(match);
            if (!scheduleIsFull(result)) {
                result.add(i, match);
            } else {
                i--;
            }
        }
    }

    private boolean scheduleIsFull(Schedule schedule){
        List<Player> players = new ArrayList<Player>(Championship.getInstance().getPlayers());
        for (Match match : schedule) {
            List<Player> currentPlayers = new ArrayList<Player>();
            currentPlayers.addAll(match.getTeam1());
            currentPlayers.addAll(match.getTeam2());
            players.removeAll(currentPlayers);
        }
        return players.isEmpty();
    }

    private Schedule createHead() {
        Schedule result = new Schedule();
        int i = 0;
        Schedule forecast = Championship.getInstance().getForecast();
        do {
            if (forecast.size() <= i) break;
            Match match = forecast.get(i);
            result.add(match);
            i++;
        } while (!scheduleIsFull(result));
        return result;
    }

    public Schedule playing(Player player) {
        Schedule schedule = new Schedule();
        for (Match match : this) {
            if (match.getTeam1().contains(player) || match.getTeam2().contains(player)) {
                schedule.add(match);
            }
        }
        return schedule;
    }

    public Set<Team> getTeams(Player player) {
        Set<Team> set = new HashSet<Team>();
        for (Match match : this) {
            Team team1 = match.getTeam1();
            Team team2 = match.getTeam2();
            if (team1.contains(player)) {
                set.add(team1);
            }
            if (team2.contains(player)) {
                set.add(team2);
            }
        }
        return set;
    }
}
