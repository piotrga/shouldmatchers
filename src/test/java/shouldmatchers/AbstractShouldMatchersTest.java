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

    @Test
    public void shouldThrow(){
        TestClass1 object = new TestClass1();
        the(object).shouldThrow(SomeDistinctException.class).when().someBuggyMethod();
        the(object).shouldThrowWhen().someBuggyMethod();
    }

    @Test
    public void shouldMatching(){
        TestClass1 object = new TestClass1();
        the(object).shouldThrowMatching("[0-9]+ is too big").when().someBuggyMethodWithMessage();
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowWhenExceptionDoesntMatch(){
        TestClass1 object = new TestClass1();
        the(object).shouldThrow(SomeOtherDistinctException.class).when().someBuggyMethod();
        the(object).shouldThrowWhen().someBuggyMethod();
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowWhenNotThrows(){
        TestClass1 object = new TestClass1();
        the(object).shouldThrowWhen().someNOTBuggyMethod(8);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowWhenNotThrowsForSpecificException(){
        TestClass1 object = new TestClass1();
        the(object).shouldThrow(SomeDistinctException.class).when().someNOTBuggyMethod(8);
    }

    static class SomeDistinctException extends RuntimeException{}
    static class SomeOtherDistinctException extends RuntimeException{}

    static class TestClass1{
        public void someBuggyMethod(){
            throw new SomeDistinctException();
        }

        public void someBuggyMethodWithMessage(){
            throw new RuntimeException("The number 78 is too big");
        }

        public int someNOTBuggyMethod(int x){
            return x+89;
        }
    }

}
