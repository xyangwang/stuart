package io.stuart.utils;

import org.apache.commons.lang3.StringUtils;

import io.stuart.consts.ParamConst;

public class LogUtil {

    public static String level(String level) {
        String result = ParamConst.LOG_LEVEL_INFO;

        if (StringUtils.isBlank(level)) {
            return result;
        }

        // to upper case
        String upper = level.toUpperCase();

        switch (upper) {
        case ParamConst.LOG_LEVEL_ALL:
            result = ParamConst.LOG_LEVEL_ALL;
            break;
        case ParamConst.LOG_LEVEL_DEBUG:
            result = ParamConst.LOG_LEVEL_DEBUG;
            break;
        case ParamConst.LOG_LEVEL_ERROR:
            result = ParamConst.LOG_LEVEL_ERROR;
            break;
        case ParamConst.LOG_LEVEL_FATAL:
            result = ParamConst.LOG_LEVEL_FATAL;
            break;
        case ParamConst.LOG_LEVEL_INFO:
            result = ParamConst.LOG_LEVEL_INFO;
            break;
        case ParamConst.LOG_LEVEL_OFF:
            result = ParamConst.LOG_LEVEL_OFF;
            break;
        case ParamConst.LOG_LEVEL_TRACE:
            result = ParamConst.LOG_LEVEL_TRACE;
            break;
        case ParamConst.LOG_LEVEL_WARN:
            result = ParamConst.LOG_LEVEL_WARN;
            break;
        default:
            result = ParamConst.LOG_LEVEL_INFO;
            break;
        }

        return result;
    }

}
