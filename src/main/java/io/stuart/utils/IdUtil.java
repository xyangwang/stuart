package io.stuart.utils;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import io.stuart.consts.MsgConst;
import io.vertx.mqtt.MqttEndpoint;

public class IdUtil {

    public static UUID uuid(String src) {
        if (StringUtils.isBlank(src)) {
            return null;
        }

        return UUID.fromString(src);
    }

    public static boolean validateClientId(String clientId) {
        if (clientId == null || clientId.length() == 0) {
            return false;
        }

        return true;
    }

    public static int nextMessageId(MqttEndpoint endpoint) {
        if (endpoint == null || !endpoint.isConnected()) {
            return -1;
        }

        int current = endpoint.lastMessageId();

        return ((current % MsgConst.MAX_MESSAGE_ID) != 0) ? current + 1 : 1;
    }

}
