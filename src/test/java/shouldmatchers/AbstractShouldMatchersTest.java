package shouldmatchers;

import org.junit.Test;

import static shouldmatchers.ShouldMatchers.the;

@SuppressWarnings({"RedundantCast"})
public class AbstractShouldMatchersTest {

    @Test
    public void equal() {
        the((Object)1).shouldBeEqual((Object)(2-1));
    }

    @Test(expected = AssertionError.class)
    public void notEqual() {
        the((Object)1).shouldBeEqual((Object)(2));
    }
}
