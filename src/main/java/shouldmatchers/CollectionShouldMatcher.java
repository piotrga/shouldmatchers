package shouldmatchers;

import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static shouldmatchers.ShouldMatchers.the;

public class CollectionShouldMatcher<E> extends IterableShouldMatcher<Collection<E>>{
    public CollectionShouldMatcher(Collection<E> acctual) {
        super(acctual);
    }

    public void shouldHaveOneElementOnly() {
        assertThat(acctual +" should have only one element", acctual.size(), equalTo(1));
    }
}
