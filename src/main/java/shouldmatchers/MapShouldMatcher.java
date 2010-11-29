package shouldmatchers;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings({"StringConcatenationInsideStringBufferAppend", "unchecked"})
public class MapShouldMatcher extends AbstractShouldMatcher<Map>{

    public MapShouldMatcher(Map acctual) { super(acctual); }

    public void shouldBeEqual(Object... mapValues) {
        Map expected = Sugar.map(mapValues);
        shouldBeEqual(expected);
    }

    public void shouldBeEqual(Map expected) {
        assertThat(DiffVisualiser.differenceToString(acctual, expected), acctual, equalTo(expected));
    }


}
