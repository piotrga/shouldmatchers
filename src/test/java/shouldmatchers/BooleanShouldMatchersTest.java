package shouldmatchers;

import org.junit.Test;

import static shouldmatchers.ShouldMatchers.assertThe;
import static shouldmatchers.ShouldMatchers.the;

public class BooleanShouldMatchersTest {

    @Test
    public void theWorksForTrue() {
        boolean catHasLongTail = true;

        the(catHasLongTail);
        assertThe(catHasLongTail);
    }

    @Test(expected = AssertionError.class)
    public void theWorksForFalse() {
        boolean catHasFiveLegs = false;
        the(catHasFiveLegs);
    }
}
