package stack;


import java.util.ArrayList;

public class BowlingGameWithRules {
    private ArrayList<Frame> frames = new ArrayList<>();
    private Frame currentFrame = new Frame();

    public int getGameScore() {
        return calculateGameScore();
    }

    public void bowl(int pins) {
        if (! this.currentFrame.isBowl1Played){
            this.currentFrame.bowl1 = pins;
            this.currentFrame.isBowl1Played = true;
            frames.add(currentFrame);

        } else {
            this.currentFrame.bowl2 = pins;
            this.currentFrame.isBowl2Played = true;
        }
        if (this.currentFrame.isBowl2Played || isFrameAStrike(this.currentFrame)){
            // in this case the frame is over, i can add the current frame to the game's frames
            currentFrame = new Frame();
        }
        if (getCurrentFrameNumber() > 10 && isSecondLastFrameStrikeOrSpare()){
            throw new GameCompleteException();
        }
    }

    private boolean isSecondLastFrameStrikeOrSpare() {
        return isFrameNotAStrikeNorASpare(frames.get(frames.size() - 2));
    }

    public boolean lastBowlIsStrike() {
        return isFrameAStrike(this.frames.get(frames.size() - 1));
    }

    public int getCurrentFrameNumber() {
        return this.frames.size();
    }

    private class Frame{
        private int bowl1, bowl2;
        private int bowl3 = -1;
        private boolean isBowl1Played;
        private boolean isBowl2Played;

        @Override
        public String toString() {
            return bowl3 == -1
                    ? ("| " + bowl1 + " | " + bowl2 + " | ")
                    : ("| " + bowl1 + " | " + bowl2 + " | " + bowl3 + " | ");
        }
    }

    public String getLastFrameInfo(){
        return this.currentFrame.toString();
    }

    private int calculateGameScore(){
        int partialScore = 0;
        System.out.println("BowlingGameWithRules.calculateGameScore");
        System.out.println("frames array length: " + frames.size());
        for(int i=0; i<frames.size();i++){
            partialScore += calculateCurrentFrameScore(i);
        }
        return partialScore;
    }

    private int calculateCurrentFrameScore(int currentFrameIndex){
        Frame currentFrame = frames.get(currentFrameIndex);
        int frameScore = currentFrame.bowl1;
        if(isFrameNotAStrikeNorASpare(currentFrame)){
            // easy case: frame is not a strike or a spare
            frameScore = currentFrame.bowl1 + currentFrame.bowl2;
        }
        if(isFrameAStrike(currentFrame)){
            // if the current frame is a strike then we need to add the next two bowls values to the current frame score
            if (currentFrameIndex < frames.size()){
                // if there is another strike after this one
                Frame nextFrame = frames.get(currentFrameIndex + 1);
                if(isFrameAStrike(nextFrame) && currentFrameIndex + 1 < frames.size()){
                    // if the next frame is another strike and there is another frame after the next
                    frameScore += nextFrame.bowl1 + frames.get(currentFrameIndex + 2).bowl1;
                } else {
                    frameScore += nextFrame.bowl1 + nextFrame.bowl2;
                }
            }

        } else{
            // the current frame is a strike but the next one is a spare
        }
        System.out.println("partialScore for frame number " + currentFrameIndex + " = " + frameScore);
        return frameScore;
    }

    private boolean isFrameNotAStrikeNorASpare(Frame currentFrame) {
        return currentFrame.bowl1 + currentFrame.bowl2 != 10;
    }

    private boolean isFrameAStrike(Frame currentFrame) {
        return currentFrame.bowl1 == 10;
    }

    public class GameCompleteException extends RuntimeException {

    }

    @Override
    public String toString(){
        String status = "######### GAME SCORE #########" + String.format("%n");
        for(Frame frame: frames){
            status += frame + String.format("%n");
        }
        status += "######### END GAME SCORE #########";
        return status;
    }

}
