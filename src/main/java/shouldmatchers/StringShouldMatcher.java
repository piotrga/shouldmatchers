package shouldmatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class StringShouldMatcher extends AbstractShouldMatcher<String>{

    public StringShouldMatcher(String acctual) { super(acctual); }

    public void shouldContain(String substring) {
        assertThat(acctual, containsString(substring));
    }


}
