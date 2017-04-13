package katas.stringcalculator;

import org.junit.Test;
import katas.stringcalculator.StringCalculator;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    private StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void emptyStringReturnsZero(){
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void theCharacterOneReturnsOne(){
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    public void oneAndTwoReturnsThree(){
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    public void twoAndOneReturnsThree(){
        assertEquals(3, stringCalculator.add("2,1"));
    }

}
