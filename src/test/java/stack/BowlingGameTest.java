package stack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingGameTest {

    private BowlingGame bowlingGame = new BowlingGame();

    @Test
    public void gameWithAllZeroesBowled() {
        bowlMany(20, 0);
        assertEquals(0, bowlingGame.getScore());
    }

    @Test
    public void gameWithAllOnesBowls() {
        bowlMany(20, 1);
        assertEquals(20, bowlingGame.getScore());
    }

    @Test
    public void gameWithAllTwosBowls() {
        bowlMany(20, 2);
        assertEquals(40, bowlingGame.getScore());
    }

    @Test
    public void gameWithOneSpare() {
        bowlingGame.bowl(9);
        bowlingGame.bowl(1);
        bowlMany(18, 1);
        assertEquals(29, bowlingGame.getScore());
    }

    @Test
    public void gameWithOneStrike() {
        bowlingGame.bowl(10);
        bowlMany(18, 1);
        assertEquals(30, bowlingGame.getScore());
    }

    @Test
    public void spareInLastFrame() {
        bowlMany(18,0);
        bowlingGame.bowl(9);
        bowlingGame.bowl(1);
        bowlingGame.bowl(1);
        assertEquals(12, bowlingGame.getScore());
    }

    @Test
    public void strikeInLastFrame() {
        bowlMany(18,0);
        bowlingGame.bowl(10);
        bowlingGame.bowl(1);
        bowlingGame.bowl(1);
        assertEquals(14, bowlingGame.getScore());
    }

    @Test(expected = BowlingGame.GameCompleteException.class)
    public void cannotBowlAfterGameIsComplete() {
        bowlMany(20,0);
        bowlingGame.bowl(0);
    }

    @Test(expected = BowlingGame.GameNotCompleteException.class)
    public void getScoreWhenGameNotComplete() {
        bowlMany(19, 0);
        bowlingGame.getScore();
    }

    @Test(expected = BowlingGame.GameNotCompleteException.class)
    public void getScoreWhenGameNotCompleteWithAdditionalBall() {
        bowlMany(18, 0);
        bowlingGame.bowl(10);
        bowlingGame.bowl(0);
        bowlingGame.getScore();
    }

    private void bowlMany(int numBowls, int pinsPerBowl) {
        for (int i=0; i<numBowls; i++)
            bowlingGame.bowl(pinsPerBowl);
    }
}
