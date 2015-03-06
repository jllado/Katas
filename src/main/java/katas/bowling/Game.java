package katas.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String[] rolls;

    public Game() {
    }

    public Game(String[] strings) {
        this.rolls = strings;
    }

    private List<String[]> getFramesFrom() {
        List<String[]> frames = new ArrayList<String[]>();
        int roll = 0;
        while (roll < rolls.length) {
            if (isStrike(rolls, roll) || isSpare(rolls, roll)) {
                frames.add(getStrikeOrSpareFrame(roll));
                roll = getNextRoll(roll);
                continue;
            }
            frames.add(getFrame(roll));
            roll += getNextRoll(roll);
        }
        return frames;
    }

    int getNextRoll(int roll) {
        if (isLastFrame(roll)) {
            return roll + 3;
        }
        if (isStrike(rolls, roll) || isSpare(rolls, roll)) {
            return roll + (isSpare(rolls, roll) ? 2 : 1);
        }
        return 2;
    }

    boolean isLastFrame(int roll) {
        return roll + 3 == rolls.length;
    }

    private String[] getFrame(int roll) {
        return new String[]{rolls[roll], rolls[roll + 1]};
    }

    private String[] getStrikeOrSpareFrame(int roll) {
        return new String[]{rolls[roll], rolls[roll + 1], rolls[roll + 2]};
    }

    public int score() {
        int score = 0;
        for (String[] frame : getFramesFrom()) {
            if (isStrike(frame)) {
                score += 10 + scoreOf(getExtraRoll(frame, 0)) + scoreOf(getExtraRoll(frame, 1));
                continue;
            }
            if (isSpare(frame)) {
                score += 10 + scoreOf(getExtraRoll(frame, 1));
                continue;
            }
            score += scoreOf(frame[(0)]) + scoreOf(frame[1]);

        }
        return score;
    }

    boolean isSpare(String[] frame) {
        return isSpare(frame, 0);
    }

    boolean isStrike(String[] frame) {
        return isStrike(frame, 0);
    }

    String getExtraRoll(String[] frame, int extraRoll) {
        return frame[1 + extraRoll];
    }

    private int scoreOf(String roll) {
        if ("-".equals(roll)) {
            return 0;
        }
        if ("X".equals(roll)) {
            return 10;
        }
        return Integer.valueOf(roll);
    }

    boolean isSpare(String[] game, int rolls) {
        return "/".equals(game[rolls + 1]);
    }

    boolean isStrike(String[] game, int rolls) {
        return "X".equals(game[rolls]);
    }
}
