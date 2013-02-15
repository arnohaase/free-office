package de.arnohaase.freeoffice.util;


public class EqHelper {
    public static boolean eq(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }

    public static boolean neq(Object o1, Object o2) {
        if (o1 == null) {
            return o2 != null;
        }
        return ! o1.equals(o2);
    }
}
