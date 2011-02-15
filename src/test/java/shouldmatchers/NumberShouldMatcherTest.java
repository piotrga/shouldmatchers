package shouldmatchers;

import org.junit.Test;

import java.math.BigDecimal;

import static shouldmatchers.ShouldMatchers.shouldFailAssertion;
import static shouldmatchers.ShouldMatchers.the;

public class NumberShouldMatcherTest {

    @Test
    public void testShouldBeGreaterThan() {
        the(1L).shouldBeGreaterThan(0f);
        the(1).shouldBeGreaterThan(0);
        the(1.1).shouldBeGreaterThan(1.0);
        the(new BigDecimal(100.1)).shouldBeGreaterThan(100);
    }

    @Test(expected = AssertionError.class)
    public void testShouldBeGreaterThanWhenNotGreaterThrows() {
        the(1L).shouldBeGreaterThan(2);
    }

    @Test
    public void testEqualityOfAnnoyingDoubles() {
        the(0.1).shouldBeEqualTo(0.1f);
        the(0.1).shouldNotBeGreaterThan(0.1);
        the(0.1d).shouldNotBeGreaterThan(0.1f);
        the(0.1d).shouldNotBeGreaterThan(new BigDecimal("0.1"));
        the(0.1d).shouldNotBeGreaterThan(new BigDecimal(0.1));
    }

    @Test
    public void testLackOfEqualityOfAnnoyingDoubles() {
        the(0.1).shouldBeEqualTo(0.1f);
        the(0.1).shouldNotBeGreaterThan(0.1);
        the(0.1d).shouldNotBeGreaterThan(0.1f);
        the(0.1d).shouldNotBeGreaterThan(new BigDecimal("0.1"));
        the(0.1d).shouldNotBeGreaterThan(new BigDecimal(0.1));
    }

    @Test
    public void testCloseTo() {
        the(0.1).shouldBeCloseTo(0.1f);
        the(0.1).shouldBeCloseTo(0.101f, 0.01);
        the(5.0).shouldBeCloseTo(7.0, 10.0);

        the(0.1).shouldBeCloseTo(0.11, 0.01);
        shouldFailAssertion(the(0.1)).shouldBeCloseTo(0.11, 0.001);
    }

}
