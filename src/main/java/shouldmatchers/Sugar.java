package shouldmatchers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static shouldmatchers.ShouldMatchers.the;

public class Sugar {

    /**
     * Makes sure the iterable has only one element and returns it.
     */
    public static <T> T onlyElementOf(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        assertTrue(iterable + " should have exactly one element but it's empty.", iterator.hasNext());
        T value = iterator.next();
        assertFalse(iterable + " should have exactly one element but has more elements.", iterator.hasNext());
        return value;
    }

    /**
     * Makes sure the array has only one element and returns it.
     */
    public static <T> T onlyElementOf(T[] array) {
        the(array.length).shouldBe(1);
        return array[0];
    }

    /**
     * Makes sure the list has only one element and returns it.
     */
    public static <T> T onlyElementOf(List<T> list) {
        the(list).shouldHaveOneElementOnly();
        return list.get(0);
    }

    public static <T> T firstElementOf(List<T> list) { return nthElementOf(list, 1); }
    public static <T> T secondElementOf(List<T> list) { return nthElementOf(list, 2); }

    // WARNING index starts from 1!
    private static <T> T nthElementOf(List<T> list, int index) {
        the(list.size()).shouldBeAtLeast(index);
        return list.get(index - 1);
    }

    @SuppressWarnings({"unchecked"})
    static Map map(Object... mapValues) {
        assertThat(mapValues.length % 2, equalTo(0));
        Map res = new HashMap();
        for(int i = 0; i < mapValues.length/2; i++){
            res.put(mapValues[i*2], mapValues[i*2+1]);
        }
        return res;
    }
}
