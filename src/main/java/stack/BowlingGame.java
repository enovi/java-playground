package stack;

import java.util.ArrayList;

public class BowlingGame {

    private int score = 0;
    private ArrayList<Frame> game = new ArrayList<>();
    private int frameCount = 0;

    public int getScore() {
        if (this.game.size() < 10){
            System.out.println(game.size());
            throw new GameNotCompleteException();
        }
        for (int i=0; i<game.size(); i++) {
            Frame f = game.get(i);
            if (i == 0){
                score += getFrameScore(f);
            } else {
                if (isStrike(game.get(i -1))){
                    // if the previous was a strike and the current frame isn't
                    score += getFrameScore(f) * 2;
                }

                if (isSpare(game.get(i -1))){
                    score += getFrameScore(f) * 2;
                }
            }
            score += getFrameScore(f);
        }
        return score;
    }

    private int getFrameScore(Frame f) {
        return isNull(f.bowl1) + isNull(f.bowl2) + isNull(f.bowl3);
    }

    private int isNull(Integer i) {
        return i == null ? 0 : i;
    }

    private boolean isStrike(Frame f) {
        return (f.bowl1 == 10);
    }

    private boolean isSpare(Frame f) {
        return (f.bowl1 + f.bowl2 == 10 && f.bowl1 < 10);
    }

    public void bowl(int pins) {
        System.out.println("Bowling a " + pins);
        Frame currentFrame = game.get(game.size());

        if (game.size() == 0) {
            System.out.println("We have no frames yet");
            currentFrame = new Frame(); System.out.println("Creating a frame");
            game.add(currentFrame);
        } else {
            currentFrame = game.get(game.size() - 1);
        }
        if (currentFrame.bowl1 == null) {
            System.out.println("bowl 1");
            currentFrame.bowl1 = pins;
            if (pins == 10) {
                currentFrame = new Frame(); System.out.println("Creating a frame");
                game.add(currentFrame);
            }
        } else if (currentFrame.bowl2 == null) {
            System.out.println("bowl 2");
            currentFrame.bowl2 = pins;
            currentFrame = new Frame(); System.out.println("Creating a frame");
            game.add(currentFrame);
        }
    }

    private class Frame {
        Integer bowl1, bowl2, bowl3;
        boolean isStrike;
        boolean isSpare;
        boolean isFrameOver;

        boolean isStrike(){
            return false;
        }
    }

    public class GameCompleteException extends RuntimeException {

    }

    public class GameNotCompleteException extends RuntimeException {

    }
}
