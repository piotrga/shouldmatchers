package shouldmatchers;

import java.util.Map;

public class ShouldMatchers {

    public static StringShouldMatcher the(String s){
        return new StringShouldMatcher(s);
    }

    public static IterableShouldMatcher the(Iterable iterable) {
        return new IterableShouldMatcher(iterable);
    }

    public static MapShouldMatcher the(Map acctual) {
        return new MapShouldMatcher(acctual);
    }

    public static ObjectShouldMatcher the(Object o) {
        return new ObjectShouldMatcher(o);
    }

    public static NumberShouldMatcher the(Number o) {
        return new NumberShouldMatcher(o);
    }


}
