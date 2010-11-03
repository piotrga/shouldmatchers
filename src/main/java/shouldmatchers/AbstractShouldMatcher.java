package shouldmatchers;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class AbstractShouldMatcher<T> {
    protected final T acctual;

    public AbstractShouldMatcher(T acctual) {
        this.acctual = acctual;
    }

    public void shouldBeEqual(Object expected) {
        assertThat(acctual, equalTo(expected));
    }

    public void shouldBe(Object expected) { shouldBeEqual(expected); }

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

    public ShouldThrow<T> shouldThrow(final Class<? extends Throwable> exceptionClass) {
        return new ShouldThrow<T>(acctual, new BaseMatcher<Throwable>(){
            public boolean matches(Object o) {
                return exceptionClass.isInstance(o);
            }

            public void describeTo(Description description) {
                description.appendValue(exceptionClass.getName());

            }
        });
    }

    /**
     * Checks if the method throws exception with message matching the regex.
     * @param regex - regular expression. It is internally wrapped: ".*" + regex + ".*"
     */
    public ShouldThrow<T> shouldThrowMatching(final String regex) {
        return new ShouldThrow<T>(acctual, new ExceptionMessageMatchesRegex(regex));

    }

    public T shouldThrowWhen() {
        return new ShouldThrow<T>(acctual, Matchers.<Throwable>anything()).when();
    }

    @SuppressWarnings({"CaughtExceptionImmediatelyRethrown"})
    public static class ShouldThrow<X> {
        private final X targetObject;
        private final Matcher<Throwable> matcher;

        public ShouldThrow(X targetObject, Matcher<Throwable> matcher) {
            this.targetObject = targetObject;
            this.matcher = matcher;
        }

        @SuppressWarnings({"unchecked"})
        public X when() {
            Enhancer e = new Enhancer();
            e.setSuperclass(targetObject.getClass());
            e.setCallback(new InvocationHandler() {
                public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                    try {
                        method.invoke(targetObject, args);
                    }
                    catch (IllegalAccessException rethrown) { throw rethrown; }
                    catch (IllegalArgumentException rethrown) { throw rethrown; }
                    catch (InvocationTargetException e) {
                        assertThat(e.getCause(), matcher);
                        return null;
                    }
                    fail("Should throw exception");
                    return null;
                }
            });
            return (X) e.create();
        }
    }

    private static class ExceptionMessageMatchesRegex extends BaseMatcher<Throwable> {
        private final String regex;

        public ExceptionMessageMatchesRegex(String regex) {
            this.regex = regex;
        }

        public boolean matches(Object o) {
            if (((Throwable) o).getMessage() == null) return false;
            return ((Throwable) o).getMessage().matches(".*"+ regex +".*");
        }

        public void describeTo(Description description) {
            description.appendText("Exception message matching ").appendValue(regex);
        }
    }
}
