package shouldmatchers;

import org.junit.Test;

import static java.util.Arrays.asList;
import static shouldmatchers.ShouldMatchers.the;
import static shouldmatchers.Sugar.firstElementOf;
import static shouldmatchers.Sugar.onlyElementOf;
import static shouldmatchers.Sugar.secondElementOf;

@SuppressWarnings({"JUnitTestMethodWithNoAssertions", "LawOfDemeter"})
public class SugarTest {
    @Test
    public void testFirstElementOf() {
        the(firstElementOf(asList(9, 8, 7))).shouldBe(9);
    }

    @Test
    public void testSecondElementOf() {
        the(secondElementOf(asList(9, 8, 7))).shouldBe(8);
    }

    @Test
    public  void theOnlyElementOf(){
        the(onlyElementOf(asList("a")));
    }

    @Test(expected = AssertionError.class)
    public  void theOnlyElementOfThrowsIfNotTrue(){
        the(onlyElementOf(asList("a", "b")));
    }
}
