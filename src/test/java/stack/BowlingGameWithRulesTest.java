package stack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BowlingGameWithRulesTest {

    BowlingGameWithRules game = new BowlingGameWithRules();

    @Test
    public void frameWithZeroBowled(){
        game.bowl(0);
        int score = game.getGameScore();
        System.out.println(game.toString());
        assertEquals(score, 0);

    }

    @Test
    public void frameWithLessThan10Bowled(){
        game.bowl(8);
        game.bowl(1);
        int score = game.getGameScore();
        assertEquals(score, 9);
        System.out.println(game.toString());
    }

    @Test
    public void singleShotIsAStrike(){
        game.bowl(10);
        assertTrue(game.lastBowlIsStrike());
    }

    @Test
    public void twoShotsMake1Frame(){
        game.bowl(1);
        game.bowl(1);
        assertEquals(game.getCurrentFrameNumber(), 1);
    }

    @Test
    public void oneStrikeMakes1Frame(){
        game.bowl(10);
        assertEquals(game.getCurrentFrameNumber(), 1);
    }

    @Test
    public void twoStrikesMake2Frames(){
        game.bowl(10);
        game.bowl(10);
        assertEquals(game.getCurrentFrameNumber(), 2);
    }

    @Test
    public void threeBowlsMake2Frames(){
        bowlMany(3, 1 );
        assertEquals(2, game.getCurrentFrameNumber());
    }

    @Test(expected = BowlingGameWithRules.GameCompleteException.class)
    public void gameIsOverAfter10Frames(){
        bowlMany(20 , 1 );
        System.out.println("Frame is: " + game.getCurrentFrameNumber());
        System.out.println("Bowling once more...");
        game.bowl(1);
    }

    @Test
    public void gameAllows3BallsInFinalFrameWithAStrike(){
        bowlMany(18 , 1 );
        System.out.println("Frame is: " + game.getCurrentFrameNumber());
        System.out.println("Striking in the last frame:");
        game.bowl(10);
        System.out.println("Frame #: " + game.getCurrentFrameNumber());
        System.out.println(game.toString());
        assertEquals(game.getCurrentFrameNumber(), 10);
        System.out.println("Bowling one more...");
        game.bowl(1);
        System.out.println(game.toString());
        assertEquals(game.getCurrentFrameNumber(), 11);
    }

    @Test
    public void gameAllows3BallsInFinalFrameWithASpare(){
        bowlMany(19 , 1 );
        System.out.println("Frame is: " + game.getCurrentFrameNumber());
        System.out.println("Bowling a spare in the last frame...");
        game.bowl(9);
        System.out.println(game.getLastFrameInfo());
        assertEquals(game.getCurrentFrameNumber(), 10);
        System.out.println(game.toString());
        game.bowl(1);
        assertEquals(11, game.getCurrentFrameNumber());
    }

    @Test
    public void strikeMultipliesTheNext3Bowls(){
        game.bowl(10);
        game.bowl(3);
        game.bowl(6);
        //The total score from these throws is:
        //Frame one: 10 + (3 + 6) = 19
        //Frame two: 3 + 6 = 9
        //TOTAL = 28
        assertEquals(28, game.getGameScore());
    }

    @Test
    public void twoConsecutiveStrikesHaveTheRightScore(){
        game.bowl(10);
        game.bowl(10);
        game.bowl(9);
        game.bowl(0);
        System.out.println(game.toString());
        //Frame one: 10 + (10 + 9) = 29
        //Frame two: 10 + (9 + 0) = 19
        //Frame three: 9 + 0 = 9
        //TOTAL = 57
        assertEquals(57, game.getGameScore());
    }

    @Test
    public void threeConsecutiveStrikesHaveTheRightScore(){
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(0);
        game.bowl(9);
        System.out.println(game.toString());
        //Frame one: 10 + (10 + 10) = 30
        //Frame two: 10 + (10 + 0) = 20
        //Frame three: 10 + (0 + 9) = 19
        //Frame four: 0 + 9 = 9
        //TOTAL = 78
        assertEquals(78, game.getGameScore());
    }

    private void bowlMany(int numberOfRounds, int pins){
        for (int i=0; i<numberOfRounds;i++){
            game.bowl(pins);
        }
    }

}
