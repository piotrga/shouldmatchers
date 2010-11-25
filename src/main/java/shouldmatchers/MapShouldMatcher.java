package shouldmatchers;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings({"StringConcatenationInsideStringBufferAppend", "unchecked"})
public class MapShouldMatcher extends AbstractShouldMatcher<Map>{

    public MapShouldMatcher(Map acctual) { super(acctual); }

    public void shouldBeEqual(Object... mapValues) {
        Map expected = flatListOfPairsToMap(mapValues);
        shouldBeEqual(expected);
    }

    public void shouldBeEqual(Map expected) {
        assertThat(differenceToString(acctual, expected), acctual, equalTo(expected));
    }

    private Map flatListOfPairsToMap(Object[] mapValues) {
        assertThat(mapValues.length % 2, equalTo(0));
        Map res = new HashMap();
        for(int i = 0; i < mapValues.length/2; i++){
            res.put(mapValues[i*2], mapValues[i*2+1]);
        }
        return res;
    }


    String differenceToString(Map m1, Map m2) {

        StringBuffer buf = new StringBuffer();
        if ( m1 == null || m2 == null ) {
            buf.append(m1).append(" != "+m2 );
            return buf.toString();
        }

        for (Object k : m1.keySet()) {
            if (!m2.containsKey(k)) {
                buf.append("M2 does not contain " + k + "\n");
            }else if (! m2.get(k).equals(m1.get(k))) {
                buf.append( String.format("m1(%s)=%s,%s != m2(%s)=%s,%s\n", k, m1.get(k), m1.get(k).getClass(), k, m2.get(k), m2.get(k).getClass()));
            }
        }
        for (Object k : m2.keySet()) {
            if (!m1.containsKey(k)) {
                buf.append("M1 does not contain " + k + "\n");
            }
        }
        return buf.toString();
    }
}
