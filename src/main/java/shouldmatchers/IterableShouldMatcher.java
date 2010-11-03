package shouldmatchers;

import java.util.Arrays;
import java.util.Comparator;

import static com.google.common.collect.Iterables.toArray;
import static java.util.Arrays.sort;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@SuppressWarnings({"unchecked"})
public class IterableShouldMatcher<T extends Iterable> extends AbstractShouldMatcher<T>{

    public IterableShouldMatcher(T acctual) { super(acctual); }

    public void shouldBeEqual(Object... list) {
        assertThat(toArray(acctual, Object.class), equalTo(list));
    }

    public boolean shouldBeOrderedAccordingTo(Comparator comparator) {
        ShouldMatchers.the(acctual).shouldNotBeNull();
        Object[] acctual = toArray(this.acctual, Object.class);
        Object[] sorted = toArray(this.acctual, Object.class);
        sort(sorted, comparator);

        if (!Arrays.equals(sorted, acctual)){
            fail(Arrays.toString(acctual) + " is not ordered according to " + comparator );
        }
        return true;
    }

    public void shouldBeOrdered(){
        if (!acctual.iterator().hasNext()) return;
        if (!(acctual.iterator().next() instanceof Comparable)) fail("Elements of this collection are not comparable. Please consider using the shouldBeOrdered(Comparator comparator) method.");

        shouldBeOrderedAccordingTo(new Comparator<Comparable>() {
            @Override
            public String toString() {
                return "Comparable comparator";
            }

            public int compare(Comparable o1, Comparable o2) {
                return o1.compareTo(o2);
            }
        });
    }

}
