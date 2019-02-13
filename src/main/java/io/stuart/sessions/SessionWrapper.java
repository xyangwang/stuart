package io.stuart.sessions;

import java.util.List;

import io.stuart.entities.cache.MqttAwaitMessage;
import io.stuart.entities.cache.MqttMessage;
import io.stuart.entities.cache.MqttRetainMessage;
import io.vertx.mqtt.MqttEndpoint;
import io.vertx.mqtt.messages.MqttPublishMessage;

public interface SessionWrapper {

    void open();

    void close();

    void closeEndpoint();

    void refreshEndpoint(MqttEndpoint endpoint);

    boolean isSameEndpoint(MqttEndpoint compare);

    void subscribeTopic(String topic, int qos);

    void unsubscribeTopic(String topic);

    void receiveQos2Message(MqttPublishMessage message);

    MqttAwaitMessage releaseQos2Message(int messageId);

    void publishMessage(MqttMessage message, int qos);

    void publishRetainMessage(List<MqttRetainMessage> retains);

    void publishCachedMessage();

    void receivePuback(int messageId);

    void receivePubrec(int messageId);

    void receivePubcomp(int messageId);

    void retry(int messageId);

    boolean isInflightFull();

    void saveInflightTimeout(int messageId);

    void deleteInflightTimeout(int messageId);

}
