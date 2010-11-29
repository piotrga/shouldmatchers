package shouldmatchers;

import java.util.Map;

public class DiffVisualiser {
    static String differenceToString(Map m1, Map m2) {

        StringBuffer buf = new StringBuffer();
        if ( m1 == null || m2 == null ) {
            buf.append(m1).append(" != "+m2 );
            return buf.toString();
        }

        for (Object k : m1.keySet()) {
            if (!m2.containsKey(k)) {
                buf.append("M2 does not contain key " + toStringWithType(k) + "\n");
            }else if (!areEqual(m2.get(k), m1.get(k))) {
                buf.append( String.format("(%s -> %s) != (%s -> %s)\n", k, toStringWithType(m1.get(k)), k, toStringWithType(m2.get(k))));
            }
        }
        for (Object k : m2.keySet()) {
            if (!m1.containsKey(k)) {
                buf.append("M1 does not contain key " + toStringWithType(k) + "\n");
            }
        }
        return buf.toString();
    }

    private static String toStringWithType(Object o){
        return classOf(o)+"<"+o+">";
    }
    private static String classOf(Object object) {
        if (object == null) return "Unknown";
        return object.getClass().getCanonicalName();
    }

    private static boolean areEqual(Object o1, Object o2) {
        if (o1 == null) return o1 == o2;
        return o1.equals(o2);
    }
}
