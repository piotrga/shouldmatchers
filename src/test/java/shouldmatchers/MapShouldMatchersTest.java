package shouldmatchers;

import org.junit.Test;

import java.util.HashMap;

import static shouldmatchers.ShouldMatchers.the;

@SuppressWarnings({"unchecked"})
public class MapShouldMatchersTest {
    HashMap map = new HashMap();
    {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
    }
    @Test
    public void equalsWithMapAsListWorks(){
        the(map).shouldBeEqual("key1", "value1", "key2", "value2", "key3", "value3");
    }

    @Test(expected = AssertionError.class)
    public void equalsWithMapAsListThrowsWhenNotEqual(){
        the(map).shouldBeEqual("key7", 89);
    }

    @Test(expected = AssertionError.class)
    public void equalsThrowsWhenNotEqual(){
        the(map).shouldBeEqual(new HashMap());
    }

    @Test(expected = AssertionError.class)
    public void equalsThrowsWhenNotEqual2(){
        the(new HashMap()).shouldBeEqual(map);
    }

}
