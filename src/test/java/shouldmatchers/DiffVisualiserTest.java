package shouldmatchers;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static shouldmatchers.DiffVisualiser.differenceToString;
import static shouldmatchers.ShouldMatchers.the;
import static shouldmatchers.Sugar.map;

public class DiffVisualiserTest {
    @Test
    public void showsNothingForEqual(){
        the(differenceToString(new HashMap(), new HashMap())).shouldBe("");
    }

    @Test
    public void leftContainsRight(){
        the(differenceToString(map(1, "val1", 2, "val2"), map(2, "val2"))).shouldMatch("M2 does not contain key java.lang.Integer<1>\n");
    }

    @Test
    public void rightContainsLeft(){
        the(differenceToString(map(2, "val2"), map(1, "val1", 2, "val2"))).shouldMatch("M1 does not contain key java.lang.Integer<1>\n");
    }

    @Test
    public void nullValues(){
        the(differenceToString(map(2, null), map(2, "val2"))).shouldNotThrow();
        the(differenceToString(map(2, "val2"), map(2, null))).shouldNotThrow();
    }

    @Test
     public void simpleDifference(){
         the(differenceToString(map(1, "val1"), map(1, new BigDecimal(2)))).shouldBeEqual("(1 -> java.lang.String<val1>) != (1 -> java.math.BigDecimal<2>)\n");
     }

}
