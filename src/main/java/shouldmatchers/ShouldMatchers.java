package shouldmatchers;

import java.util.Collection;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShouldMatchers {

    public static StringShouldMatcher the(String acctual){ return new StringShouldMatcher(acctual); }
    public static StringShouldMatcher Then(String acctual){ return the(acctual);}


    public static <E> IterableShouldMatcher<Iterable<E>> the(Iterable<E> acctual) { return new IterableShouldMatcher<Iterable<E>>(acctual); }
    public static <E> IterableShouldMatcher<Iterable<E>> Then(Iterable<E> acctual) { return the(acctual);}

    public static <E> CollectionShouldMatcher<E> the(Collection<E> acctual) { return new CollectionShouldMatcher<E>(acctual); }
    public static <E> CollectionShouldMatcher<E> Then(Collection<E> acctual) { return the(acctual); }


    public static MapShouldMatcher the(Map acctual) { return new MapShouldMatcher(acctual); }
    public static MapShouldMatcher Then(Map acctual) { return the(acctual);}


    public static NumberShouldMatcher the(Number acctual) { return new NumberShouldMatcher(acctual); }
    public static NumberShouldMatcher Then(Number acctual) { return the(acctual); }


    public static  void assertThe(Boolean acctual ){ assertThat(acctual, is(true)); }
    public static  void the(Boolean acctual ){ assertThe(acctual); }

    public static <T> AbstractShouldMatcher<T> the(T acctual ){ return new AbstractShouldMatcher<T>(acctual); }
    public static <T> AbstractShouldMatcher<T> Then(T acctual ){ return the(acctual); }

}
