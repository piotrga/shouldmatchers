package shouldmatchers;

import org.junit.Test;

import java.util.Comparator;

import static java.util.Arrays.asList;
import static shouldmatchers.ShouldMatchers.the;

public class IterableShouldMatchersTest {
    @Test
    public void shoudBeEqualWithVarArg(){
        the(asList(1, 2, 3)).shouldBeEqual(1, 2, 3);
    }

    @Test(expected = AssertionError.class)
    public void equalsThrowsWhenNotEqualWithVarArg(){
        the(asList(1, 2, 3)).shouldBeEqual(9, 8, 7);
    }

    @Test
    public void shoudBeEqual(){
        the(asList(1, 2, 3)).shouldBeEqual(asList(1, 2, 3));
    }

    @Test(expected = AssertionError.class)
    public void equalsThrowsWhenNotEqual(){
        the(asList(1, 2, 3)).shouldBeEqual(asList(9, 8, 7));
    }

    @Test
    public void isOrdered(){
        the(asList(1, 2, 3)).shouldBeOrderedAccordingTo(new IntegerComparator());
    }

    @Test
    public void isOrderedForEmpty(){
        the(asList()).shouldBeOrderedAccordingTo(new IntegerComparator());
        the(asList()).shouldBeOrdered();
    }

    @Test(expected = AssertionError.class)
    public void isNOTOrdered(){
        the(asList(2, 1, 3)).shouldBeOrderedAccordingTo(new IntegerComparator());
    }

    @Test
    public void isOrderedForComparable(){
        the(asList(1, 2, 3)).shouldBeOrdered();
    }

    @Test(expected = AssertionError.class)
    public void isOrderedForNotComparableThrows(){
        the(asList(new NotComparable(1), new NotComparable(2), new NotComparable(3))).shouldBeOrdered();
    }

    @Test(expected = AssertionError.class)
    public void isNOTOrderedForComparable(){
        the(asList(2, 1, 3)).shouldBeOrdered();
    }

    private static class IntegerComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    class NotComparable {
        NotComparable(int i) { this.i = i; }
        int i;
    }
}
