package model;

import java.util.Comparator;

public class RatingComparator implements Comparator<PlayerRating> {

    public int compare(PlayerRating p1, PlayerRating p2) {
        {
            int res = compareScore(p1, p2);
            if (res == 0){
                res = compareGamesPlayed(p1, p2);
                if (res == 0) {
                    res = compareDifference(p1, p2);
                    if (res == 0){
                        res = compareHits(p1, p2);
                    }
                }
            }
            return res;
        }
    }

    private int compareScore(PlayerRating p1, PlayerRating p2){
        return p2.getScore() - p1.getScore();
    }

    private int compareGamesPlayed(PlayerRating p1, PlayerRating p2){
        return p1.getGamesPlayed() - p2.getGamesPlayed();
    }

    private int compareDifference(PlayerRating p1, PlayerRating p2){
        return p2.getDifference() - p1.getDifference();
    }

    private int compareHits(PlayerRating p1, PlayerRating p2){
        return p2.getWinScore() - p1.getWinScore();
    }
}
