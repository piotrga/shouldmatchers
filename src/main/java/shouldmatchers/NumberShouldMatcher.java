package shouldmatchers;


import static org.junit.Assert.assertTrue;

public class NumberShouldMatcher extends AbstractShouldMatcher<Number>{

    public NumberShouldMatcher(Number acctual) {
        super(acctual);
    }

    public void shouldBeGreaterThan(Number number) {
        assertTrue(acctual + " is NOT greater than " + number + "!",acctual.doubleValue() > number.doubleValue());
    }
}
