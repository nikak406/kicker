package model;

import java.util.Comparator;

public class RatingComparator implements Comparator<PlayerRating> {

    public int compare(PlayerRating p1, PlayerRating p2) {
        {
            int res = compareScoreCoef(p1, p2);
            if (res == 0){
                res = compareDiffCoef(p1, p2);
                if (res == 0) {
                    res = compareHitsCoef(p1, p2);
                    if (res == 0){
                        res = compareGamesPlayed(p1, p2);
                    }
                }
            }
            return res;
        }
    }

    private int compareScoreCoef(PlayerRating p1, PlayerRating p2){
        return  (p2.getCoef() - p1.getCoef());
    }

    private int compareDiffCoef(PlayerRating p1, PlayerRating p2){
        return  (p2.diffCoef() - p1.diffCoef());
    }

    private int compareHitsCoef(PlayerRating p1, PlayerRating p2){
        return (p2.hitCoef() - p1.hitCoef());
    }

    private int compareGamesPlayed(PlayerRating p1, PlayerRating p2){
        return p2.getGamesPlayed() - p1.getGamesPlayed();
    }

    private int compareDifference(PlayerRating p1, PlayerRating p2){
        return p2.getDifference() - p1.getDifference();
    }

    private int compareHits(PlayerRating p1, PlayerRating p2){
        return p2.getWinScore() - p1.getWinScore();
    }
}
