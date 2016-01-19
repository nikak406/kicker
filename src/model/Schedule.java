package model;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Schedule extends TreeSet<Match> {

    public String writeSchedule(){
        return "";
        //TODO
    }
    
    public String writeTable(){
        return "";
        //TODO
    }
    
    public Set<Team> getTeams() {
        Set<Team> teams = new TreeSet<>();
        for (Match match : this) {
            teams.addAll(match.getTeams());
        }
        return teams;
    }

    public Set<Team> getTeams(Player player) {
        Set<Team> allTeams = getTeams();
        return allTeams.stream().filter(team -> team.contains(player)).collect(Collectors.toCollection(TreeSet::new));
    }
}
