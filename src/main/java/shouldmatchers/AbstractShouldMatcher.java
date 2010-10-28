package shouldmatchers;

import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AbstractShouldMatcher<T> {
    protected final T acctual;

    public AbstractShouldMatcher(T acctual) {
        this.acctual = acctual;
    }

    public void shouldBeEqual(Object expected) {
        assertThat(acctual, equalTo(expected));
    }

    public void shouldNotBeNull() {
        assertNotNull(acctual);
    }

    public void shouldBe(Matcher<T> matcher) {
        assertThat(acctual, matcher);
    }

    public void shouldNotBe(Matcher<T> matcher) {
        assertThat(acctual, not(matcher));
    }

    public void should(Matcher<T> matcher) {
        assertThat(acctual, matcher);
    }

}
