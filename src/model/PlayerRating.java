package model;

public class PlayerRating extends Player {

    public int getDifference() {
        return difference;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getLoses() {
        return loses;
    }

    public int getLoseScore() {
        return loseScore;
    }

    public int getScore() {
        return score;
    }

    public int getWins() {
        return wins;
    }

    public int getWinScore() {
        return winScore;
    }

    private int gamesPlayed;
    private int wins = 0;
    private int loses = 0;
    private int winScore = 0;
    private int loseScore = 0;
    private int difference = 0;
    private int score = 0;

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    private int coef = 0;

    public PlayerRating(Player player, Schedule schedule) {
        super(player.getName());
        this.gamesPlayed = schedule.size();
        for (Match match : schedule) {
            boolean win = match.playerWined(player);
            Score score = match.getScore();
            int score1 = score.score1;
            int score2 = score.score2;
            if (win){
                wins++;
                winScore += Math.max(score1, score2);
                loseScore += Math.min(score1, score2);
                this.score++;
            } else{
                loses++;
                winScore += Math.min(score1, score2);
                loseScore += Math.max(score1, score2);
            }
            if (gamesPlayed!=0){
                float x = ((float)wins) / (float)gamesPlayed;
                x = x * 100;
                coef = (int) x;
            }
            difference += winScore - loseScore;
        }
    }
}
