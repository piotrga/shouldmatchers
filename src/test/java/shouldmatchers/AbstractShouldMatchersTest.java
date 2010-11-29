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
        Object solutionToThisProblem = 1;
        the(solutionToThisProblem).shouldBeEqual(2);
        the(solutionToThisProblem).shouldBe(2);
    }

    @Test
    public void shouldWithMatcher(){
        String solution = "123";
        the(solution).shouldBe(equalTo("123"));
        the(solution).should(containsString("123"));
        TestClass1 someClassOtherThanString = new TestClass1(1);
        the(someClassOtherThanString).shouldBe(equalTo(someClassOtherThanString));
        Then(solution).shouldNotBe(equalTo("789"));
    }

    @Test
    public void shouldThrow(){
        TestClass1 object = new TestClass1(2);
        the(object).shouldThrow(SomeDistinctException.class).when().someBuggyMethod();
        the(object).shouldThrowWhen().someBuggyMethod();
    }

    @Test
    public void shouldThrowMatching(){
        TestClass1 object = new TestClass1(2);
        the(object).shouldThrowMatching("[0-9]+ is too big").when().someBuggyMethodWithMessage();
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowWhenExceptionDoesntMatch(){
        TestClass1 object = new TestClass1(2);
        the(object).shouldThrow(SomeOtherDistinctException.class).when().someBuggyMethod();
        the(object).shouldThrowWhen().someBuggyMethod();
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowWhenNotThrows(){
        TestClass1 object = new TestClass1(2);
        the(object).shouldThrowWhen().someNOTBuggyMethod(8);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowWhenNotThrowsForSpecificException(){
        TestClass1 object = new TestClass1(2);
        the(object).shouldThrow(SomeDistinctException.class).when().someNOTBuggyMethod(8);
    }

    static class SomeDistinctException extends RuntimeException{}
    static class SomeOtherDistinctException extends RuntimeException{}

    static class TestClass1{
        TestClass1(int arg1) {
        }

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
