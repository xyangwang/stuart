package io.stuart.utils;

import java.io.File;

public class DirUtil {

    public static boolean mkdirs(String path) {
        File file = new File(path);

        if (file.exists()) {
            return false;
        }

        return file.mkdirs();
    }

}
