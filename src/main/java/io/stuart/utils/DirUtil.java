package io.stuart.utils;

import java.io.File;

import io.stuart.exceptions.StartException;

public class DirUtil {

    public static boolean mkdirs(String path) {
        File file = new File(path);

        if (file.exists()) {
            return false;
        }

        try {
            return file.mkdirs();
        } catch (SecurityException e) {
            throw new StartException(e);
        }
    }

}
