package shouldmatchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AbstractShouldMatcher<T> {
    protected final T acctual;

    public AbstractShouldMatcher(T acctual) {
        this.acctual = acctual;
    }

    public void shouldBeEqual(T compareTo) {
        assertThat(acctual, equalTo(compareTo));
    }

    public void shouldNotBeNull() {
        assertNotNull(acctual);
    }
}
