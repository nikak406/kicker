package model;

import java.util.Scanner;

public class Score {
    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    int score1;
    int score2;

    public Score() {
    }

    public Score(int a, int b) {
        assert (a >= 0);
        assert (b >= 0);
        this.score1 = a;
        this.score2 = b;
    }

    public Score(String score) {
        Scanner scanner = new Scanner(transform(score));
        while ((!scanner.hasNextInt())  && scanner.hasNext()){
            scanner.next();
        }
        score1 = scanner.nextInt();
        while ((!scanner.hasNextInt()) && scanner.hasNext()){
            scanner.next();
        }
        score2 = scanner.nextInt();
    }

    private String transform(String score) {
        StringBuilder sb = new StringBuilder();
        for (char c : score.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return " " + score1 + ":" + score2 + " ";
    }

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<score>\n");
        sb.append("<score1>").append(score1).append("</score1>\n");
        sb.append("<score2>").append(score2).append("</score2>\n");
        sb.append("</score>\n");
        return sb.toString();
    }
}
