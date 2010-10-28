package shouldmatchers;

import org.junit.Test;

import java.math.BigDecimal;

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
}
