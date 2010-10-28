package shouldmatchers;

import org.junit.Test;

import static shouldmatchers.ShouldMatchers.the;

public class StringShouldMatchersTest {

    @Test
    public void contains(){
        the("123").shouldContain("12");
    }

    @Test(expected = AssertionError.class)
    public void doesntContain(){
        the("123").shouldContain("897");
    }
}
