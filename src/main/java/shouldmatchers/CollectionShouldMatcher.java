package shouldmatchers;

import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CollectionShouldMatcher<E> extends IterableShouldMatcher<Collection<E>>{
    public CollectionShouldMatcher(Collection<E> acctual) {
        super(acctual);
    }

    public void shouldHaveOneElementOnly() {
        assertThat(acctual +" should have only one element", acctual.size(), equalTo(1));
    }

    public void shouldHaveSize(int expectedSize) {
        if (acctual.size()!= expectedSize) throw new AssertionError(String.format("Expected collection of size %d\n Got collection of size %d, %s", expectedSize, acctual.size(), acctual.toString()));
    }
}
