package shouldmatchers;

import java.util.List;

import static shouldmatchers.ShouldMatchers.the;

public class Sugar {
    public static <T> T firstElementOf(List<T> list) { return nthElementOf(list, 1); }
    public static <T> T secondElementOf(List<T> list) { return nthElementOf(list, 2); }

    // WARNING index starts from 1!
    private static <T> T nthElementOf(List<T> list, int index) {
        the(list.size()).shouldBeAtLeast(index);
        return list.get(index - 1);
    }
}
