package shouldmatchers;

import org.hamcrest.CoreMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class StringShouldMatcher extends AbstractShouldMatcher<String>{

    public StringShouldMatcher(String acctual) { super(acctual); }


    public void shouldBeEqual(String compareTo) {
        assertThat(acctual, CoreMatchers.equalTo(compareTo));
    }

    public void shouldContain(String substring) {
        assertThat(acctual, containsString(substring));
    }
}
