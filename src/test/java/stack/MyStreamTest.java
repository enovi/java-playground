package stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

public class MyStreamTest {
    List<String> stringCollection = new ArrayList<>();
    private boolean isSetupRun = false;

    @Before
    public void setup(){
        if (isSetupRun){
            return;
        }
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        stringCollection.stream().sorted().forEach(System.out::println);
        isSetupRun = true;
    }

    @Test
    public void filterByAReturns2Strings(){
        assertEquals((long) 2,
                stringCollection
                    .stream()
                    .filter((s -> s.startsWith("a")))
                    .count());
    }

    @Test
    public void sortedReturnsTheFirstCorrectElement(){
        assertEquals("aaa1",
                stringCollection.stream().sorted().findFirst().get());
    }

    @Test
    public void filterReturnsTheExpectedElements(){
        String[] expected = { "bbb1", "bbb2", "bbb3" };
        assertArrayEquals(expected, stringCollection.stream().filter(s -> s.startsWith("b")).sorted().toArray());
    }

    @Test
    public void mapUppercaseReturnsAnUppercaseString(){
        assertArrayEquals(new String[] { "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "BBB1", "AAA2", "AAA1"},
                stringCollection
                    .stream()
                    .map(String::toUpperCase)
                    .sorted(Comparator.reverseOrder())
                    .toArray());
    }

    @Test
    public void mapRemoveLastCharReturnsTheStringsWithouthAChar(){
        assertArrayEquals(new String[] { "ddd", "aaa", "bbb","aaa","bbb","cc","bbb","ddd"},
                stringCollection
                        .stream()
                        .map(s -> s.substring(0, s.length()-1))
                        .toArray());
    }

    @Test
    public void matchingTheRightElements(){
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));
        assertTrue(anyStartsWithA);

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));
        assertFalse(allStartsWithA);

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));
        assertTrue(noneStartsWithZ);
    }

    @Test
    public void thereAre3ItemsStartingWithB(){
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        Assert.assertEquals(3, startsWithB);
    }

    @Test
    public void reduceIsAddingTheRightChar(){
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        assertEquals("aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2", reduced.get());
    }

    @Test
    public void reducingUsingAConcatenationWorks(){
        String[] myArray = { "this", "is", "a", "sentence" };
        assertEquals("this is a sentence",
                Arrays
                        .stream(myArray)
                        .reduce((a,b) -> a + " " + b).get());
    }


}
