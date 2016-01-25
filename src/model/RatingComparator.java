package model;

import java.util.Comparator;

public class RatingComparator implements Comparator<PlayerRating> {

    @Override
    public int compare(PlayerRating p1, PlayerRating p2) {
        {
            if (p2.getCoef() != p1.getCoef()){
                return p2.getCoef() - p1.getCoef();
            } else {
                float x1 = (p1.getGamesPlayed() == 0)? 0 : ((float)p1.getDifference()/(float)p1.getGamesPlayed());
                float x2 = (p2.getGamesPlayed() == 0)? 0 : ((float)p2.getDifference()/(float)p2.getGamesPlayed());
                return (int) Math.signum(x2 - x1);
            }
        }
    }
}
