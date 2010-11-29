package shouldmatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StringShouldMatcher extends AbstractShouldMatcher<String>{

    public StringShouldMatcher(String acctual) { super(acctual); }

    public void shouldContain(String substring) {
        assertThat(acctual, containsString(substring));
    }


    public void shouldMatch(String regex) {
        assertTrue("'"+acctual+"' should match regex '"+regex+"'", acctual.matches(regex));
    }

    public void shouldNotThrow() {
        // this does nothing it's just a sugar
    }
}
