package stack;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class MyStackTest {

    private MyStack myStack = new MyStack();

    @Test
    public void stackInitiallyEmpty() {
        assertEquals(0, myStack.size());
    }

    @Test
    public void addingToStackIncrementsSize() {
        myStack.add(null);
        assertEquals(1, myStack.size());
        myStack.add(null);
        assertEquals(2, myStack.size());
    }

    @Test
    public void poppingFromStackDecrementsSize() {
        myStack.add(null);
        myStack.pop();
        assertEquals(0, myStack.size());
    }

    @Test
    public void poppingReturnsObjectThatWasLastAdded() {
        String i = "1";
        myStack.add(i);
        String k = (String) myStack.pop();
        assertSame(k, i);
    }

    @Test(expected = IllegalStateException.class)
    public void poppingEmptyStackThrowsIllegalStateException() {
        myStack.pop();
    }

    @Test
    public void popPullsValuesInReverseOrderThatTheyWereAdded() {
        String s1 = "one";
        String s2 = "two";
        myStack.add(s1);
        myStack.add(s2);
        assertEquals(s2, myStack.pop());
        assertEquals(s1, myStack.pop());
    }

    @Test
    public void popThenAddThenPopAgain() {
        String s1 = "one";
        String s2 = "two";
        myStack.add(s1);
        myStack.pop();
        myStack.add(s2);
        assertEquals(s2, myStack.pop());
    }

    @Test
    public void popTheSameObjectTwice() {
        String s1 = "one";
        String s2 = "two";
        myStack.add(s1);
        myStack.add(s2);
        myStack.add(s1);
        assertEquals(s1, myStack.pop());
        assertEquals(s2, myStack.pop());
    }

}
