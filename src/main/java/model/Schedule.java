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
    
    public Set<Team> getTeams() {
        Set<Team> teams = new HashSet<>();
        for (Match match : this) {
            teams.addAll(match.getTeams());
        }
        return teams;
    }

    public Set<Team> getTeams(Player player) {
        Set<Team> allTeams = getTeams();
        return allTeams.stream().filter(team -> team.contains(player)).collect(Collectors.toCollection(HashSet::new));
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
        return this.stream()
                .filter(match -> (match.getTeam1().contains(player) || match.getTeam2().contains(player)))
                .collect(Collectors.toCollection(Schedule::new));
    }
}
