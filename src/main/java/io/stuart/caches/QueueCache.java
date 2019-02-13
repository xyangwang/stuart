package io.stuart.caches;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteQueue;
import org.apache.ignite.configuration.CollectionConfiguration;

import io.stuart.caches.impl.QueueCacheImpl;
import io.stuart.entities.cache.MqttMessage;
import io.stuart.ext.collections.BoundedIgniteQueue;
import io.vertx.mqtt.messages.MqttPublishMessage;

public interface QueueCache {

    static QueueCache create(Ignite ignite, CollectionConfiguration cfg) {
        return new QueueCacheImpl(ignite, cfg);
    }

    BoundedIgniteQueue<MqttMessage> open(String clientId);

    void close(String clientId);

    boolean enqueue(MqttPublishMessage message, String clientId, int qos);

    boolean enqueue(MqttMessage message, String clientId, int qos);

    IgniteQueue<MqttMessage> queue(String clientId, boolean create);

    BoundedIgniteQueue<MqttMessage> wrapper(IgniteQueue<MqttMessage> queue);

}
