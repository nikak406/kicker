package model;

public class Team {

    public Player getAttacker() {
        return attacker;
    }

    public Player getDefender() {
        return defender;
    }

    private Player attacker;
    private Player defender;

    public Team(Player attacker, Player defender) {
        assert (attacker != defender);
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public String toString() {
        return "" + attacker + " " + defender;
    }

    public boolean contains(Player player) {
        return (attacker == player || defender == player);
    }

    public Player getAnotherPlayer(Player player) {
        if (defender == player) {
            return attacker;
        }else {
            if (attacker == player) {
                return defender;
            }else {
                return null;
            }
        }
    }
}
