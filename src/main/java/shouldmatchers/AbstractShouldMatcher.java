package shouldmatchers;

import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.proxy.*;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.objenesis.ObjenesisStd;

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
    public void shouldBeEqualTo(Object expected) { shouldBeEqual(expected); }

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

    public ShouldThrow<T> shouldThrow(Throwable throwable) {
        return new ShouldThrow<T>(acctual, Matchers.equalTo(throwable));
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
          private static final NamingPolicy NAMING_POLICY_THAT_ALLOWS_IMPOSTERISATION_OF_CLASSES_IN_SIGNED_PACKAGES = new DefaultNamingPolicy() {
        @Override
        public String getClassName(String prefix, String source, Object key, Predicate names) {
            return "org.jmock.codegen." + super.getClassName(prefix, source, key, names);
        }
    };

    private static final CallbackFilter IGNORE_BRIDGE_METHODS = new CallbackFilter() {
        public int accept(Method method) {
            return method.isBridge() ? 1 : 0;
        }
    };
        private final X targetObject;
        private final Matcher<Throwable> matcher;

        public ShouldThrow(X targetObject, Matcher<Throwable> matcher) {
            this.targetObject = targetObject;
            this.matcher = matcher;
        }

        @SuppressWarnings({"unchecked"})
        public X when() {
            Enhancer enhancer = new Enhancer();
            enhancer.setUseFactory(true);
            Class<? extends Object> targetClass = targetObject.getClass();
            enhancer.setSuperclass(targetClass);
            enhancer.setCallbackTypes(new Class[]{InvocationHandler.class, NoOp.class});
            enhancer.setCallbackFilter(IGNORE_BRIDGE_METHODS);
            if (targetClass.getSigners() != null) {
                enhancer.setNamingPolicy(NAMING_POLICY_THAT_ALLOWS_IMPOSTERISATION_OF_CLASSES_IN_SIGNED_PACKAGES);
            }


            Class clazz = enhancer.createClass();
            Factory proxy = (Factory) new ObjenesisStd().newInstance(clazz);
            proxy.setCallbacks(new Callback[]{
                    new InvocationHandler() {
                        public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                            try {
                                method.invoke(targetObject, args);
                            } catch (IllegalAccessException rethrown) {
                                throw rethrown;
                            } catch (IllegalArgumentException rethrown) {
                                throw rethrown;
                            } catch (InvocationTargetException e) {
                                assertThat(e.getCause(), matcher);
                                return null;
                            }
                            fail("Should throw exception");
                            return null;
                        }
                    }, NoOp.INSTANCE

            });

            return (X) proxy;
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
