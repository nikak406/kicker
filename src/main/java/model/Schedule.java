package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@XmlRootElement
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
