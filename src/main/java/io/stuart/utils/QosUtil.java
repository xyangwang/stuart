package io.stuart.utils;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.stuart.config.Config;

public class QosUtil {

    public static int calculate(int messageQos, int topicQos) {
        if (Config.isSessionUpgradeQos()) {
            return upgrade(messageQos, topicQos);
        } else {
            return downgrade(messageQos, topicQos);
        }
    }

    public static MqttQoS calculate(MqttQoS messageQos, MqttQoS topicQos) {
        if (Config.isSessionUpgradeQos()) {
            return upgrade(messageQos, topicQos);
        } else {
            return downgrade(messageQos, topicQos);
        }
    }

    private static int downgrade(int messageQos, int topicQos) {
        return messageQos < topicQos ? messageQos : topicQos;
    }

    private static int upgrade(int messageQos, int topicQos) {
        return messageQos > topicQos ? messageQos : topicQos;
    }

    private static MqttQoS downgrade(MqttQoS messageQos, MqttQoS topicQos) {
        if (messageQos == null || topicQos == null) {
            return null;
        }

        // get message qos int value
        int iMessageQos = messageQos.value();
        // get topic qos int value
        int iTopicQos = topicQos.value();

        return MqttQoS.valueOf(iMessageQos < iTopicQos ? iMessageQos : iTopicQos);
    }

    private static MqttQoS upgrade(MqttQoS messageQos, MqttQoS topicQos) {
        if (messageQos == null || topicQos == null) {
            return null;
        }

        // get message qos int value
        int iMessageQos = messageQos.value();
        // get topic qos int value
        int iTopicQos = topicQos.value();

        return MqttQoS.valueOf(iMessageQos > iTopicQos ? iMessageQos : iTopicQos);
    }

}
