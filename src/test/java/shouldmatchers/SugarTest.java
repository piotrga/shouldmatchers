package shouldmatchers;

import org.junit.Test;

import static java.util.Arrays.asList;
import static shouldmatchers.ShouldMatchers.the;
import static shouldmatchers.Sugar.firstElementOf;
import static shouldmatchers.Sugar.secondElementOf;

public class SugarTest {
    @Test
    public void testFirstElementOf() {
        the(firstElementOf(asList(9, 8, 7))).shouldBe(9);
    }

    @Test
    public void testSecondElementOf() {
        the(secondElementOf(asList(9, 8, 7))).shouldBe(8);
    }
}
