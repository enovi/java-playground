package string.calulator.kata;

import org.junit.Test;
import string.calculator.kata.StringCalculator;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    public StringCalculator stringCalculator = new StringCalculator();

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


}
