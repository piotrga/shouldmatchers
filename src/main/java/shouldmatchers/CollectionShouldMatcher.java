package shouldmatchers;

import java.util.Collection;

import static shouldmatchers.ShouldMatchers.the;

public class CollectionShouldMatcher<E> extends IterableShouldMatcher<Collection<E>>{
    public CollectionShouldMatcher(Collection<E> acctual) {
        super(acctual);
    }

    public void shouldHaveOneElementOnly() {
        the(acctual.size()).shouldBe(1);
    }
}
