package shouldmatchers;


import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class NumberShouldMatcher extends AbstractShouldMatcher<Number>{

    public static final double COMPARISON_PRECISION = 0.0000001;

    public NumberShouldMatcher(Number acctual) {
        super(acctual);
    }

    public void shouldBeGreaterThan(Number number) {
        assertTrue(acctual + " is NOT greater than " + number + "[precision is "+COMPARISON_PRECISION+"]!", acctual.doubleValue() - number.doubleValue() > COMPARISON_PRECISION);
    }

    public void shouldBeAtLeast(Number expected) {
        assertTrue(acctual + " is NOT greater than " + expected + "[precision is "+COMPARISON_PRECISION+"]!", acctual.doubleValue() - expected.doubleValue()>= COMPARISON_PRECISION);
    }

    public void shouldNotBeGreaterThan(Number number) {
        assertTrue(acctual + " is greater than " + number + "[precision is "+COMPARISON_PRECISION+"]!", acctual.doubleValue() - number.doubleValue() < COMPARISON_PRECISION);
    }

    @Override
    public void shouldBeEqual(Object expected) {
        assertThat(acctual +" != " + expected ,expected, is(Number.class));
        shouldBeCloseTo((Number) expected);
    }

    public void shouldBeCloseTo(Number expected) {
        assertThat(acctual +" != " + expected, acctual.doubleValue(), closeTo(expected.doubleValue(), COMPARISON_PRECISION));
    }

    public void shouldBeCloseTo(Number expected, Double error) {
        assertThat(acctual +" != " + expected, acctual.doubleValue(), closeTo(expected.doubleValue(), error));
    }

    private String toString(String matcherName, Object expected) {
        return "<"+acctual +">." + matcherName +"("+expected+")";
    }
}
