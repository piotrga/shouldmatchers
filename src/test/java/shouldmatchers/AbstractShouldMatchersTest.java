package shouldmatchers;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static shouldmatchers.ShouldMatchers.Then;
import static shouldmatchers.ShouldMatchers.the;

@SuppressWarnings({"RedundantCast"})
public class AbstractShouldMatchersTest {

    @Test
    public void equal() {
        the((Object)1).shouldBeEqual(2-1);
    }

    @Test(expected = AssertionError.class)
    public void notEqual() {
        the((Object)1).shouldBeEqual(2);
    }

    @Test
    public void shouldWithMatcher(){
        the("123").shouldBe(equalTo("123"));
        the("123").should(containsString("123"));
        AbstractShouldMatchersTest o = new AbstractShouldMatchersTest();
        the(o).shouldBe(equalTo(o));
        Then("123").shouldNotBe(equalTo("789"));
    }


}
