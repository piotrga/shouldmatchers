package shouldmatchers;

import org.junit.Test;

import static java.util.Arrays.asList;
import static shouldmatchers.ShouldMatchers.the;

public class CollectionShouldMatcherTest {

    @Test
    public void shouldHaveOneElementOnly() {
        the(asList(1)).shouldHaveOneElementOnly();
        the(the(asList(1,2))).failsAssertion().shouldHaveOneElementOnly();
    }

    @Test
    public void shouldHaveSize() {
        the(asList("a", "b", "c")).shouldHaveSize(3);
        the(the(asList(1,2))).failsAssertion().shouldHaveSize(1);
    }
}
