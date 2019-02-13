package io.stuart.utils;

import java.util.UUID;

public class RowUtil {

    public static UUID getUUID(Object obj) {
        if (obj == null) {
            return null;
        }

        return IdUtil.uuid(obj.toString());
    }

    public static String getStr(Object obj) {
        if (obj == null) {
            return null;
        }

        return obj.toString();
    }

    public static boolean getBool(Object obj) {
        if (obj == null) {
            return false;
        }

        return Boolean.parseBoolean(obj.toString());
    }

    public static int getInt(Object obj) {
        if (obj == null) {
            return 0;
        }

        return Integer.parseInt(obj.toString());
    }

    public static long getLong(Object obj) {
        if (obj == null) {
            return 0L;
        }

        return Long.parseLong(obj.toString());
    }

}
