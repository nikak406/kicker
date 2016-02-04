package model;

import java.util.ArrayList;

public class Schedule extends ArrayList<Match> {

    public Schedule() {
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Schedule:\n");
        for (Match match : this) {
            builder.append(match).append("\n");
        }
        return builder.toString();
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
}
