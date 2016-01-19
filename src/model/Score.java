package model;

import java.util.Scanner;

public class Score {
    int score1;
    int score2;

    public Score(int a, int b) {
        assert (a > 0);
        assert (b > 0);
        this.score1 = a;
        this.score2 = b;
    }

    public Score(String score) {
        Scanner scanner = new Scanner(score);
        score1 = scanner.nextInt();
        score2 = scanner.nextInt();
    }

    boolean firstTeamWin() {
        return (score1 - score2 > 0);
    }

    @Override
    public String toString() {
        return " " + score1 + ":" + score2 + " ";
    }
}
