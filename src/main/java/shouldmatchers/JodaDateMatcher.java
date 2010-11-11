package shouldmatchers;

import org.joda.time.ReadableInstant;

import static org.junit.Assert.assertTrue;

public class JodaDateMatcher extends AbstractShouldMatcher<ReadableInstant>{
    public JodaDateMatcher(ReadableInstant acctual) {
        super(acctual);
    }

    public void shouldBeAfter(ReadableInstant laterDate) {
        assertTrue(acctual+" should be after "+ laterDate, acctual.isAfter(laterDate));
    }
}
