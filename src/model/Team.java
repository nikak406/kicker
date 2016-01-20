package model;

import java.util.ArrayList;
import java.util.List;

public class Team extends ArrayList<Player>{

    public Team(Player attacker, Player defender) {
        super(2);
        assert (attacker != defender);
        this.add(attacker);
        this.add(defender);
    }

    @Override
    public String toString() {
        return "" + this.get(0) + " " + this.get(1);
    }

    public Player getAnotherPlayer(Player player) {
        int index = this.indexOf(player);
        if (index == -1){
            return null;
        }
        return (this.get((index + 1) % 2));
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || o.getClass() != this.getClass()) && this.containsAll((Team) o);
    }

    @Override
    public int hashCode() {
        return (int)(this.get(0).hashCode() + this.get(1).hashCode());
    }

    public boolean canPlay(Team team){
        List<Player> copy = new ArrayList<>(this);
        copy.retainAll(team);
        return copy.isEmpty();
    }
}
