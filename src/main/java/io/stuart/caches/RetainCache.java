package io.stuart.caches;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.RetainCacheImpl;
import io.stuart.entities.cache.MqttRetainMessage;

public interface RetainCache {

    static RetainCache create(Ignite ignite, CacheConfiguration<String, MqttRetainMessage> cfg) {
        return new RetainCacheImpl(ignite, cfg);
    }

    void save(MqttRetainMessage message);

    boolean delete(String topic);

    List<MqttRetainMessage> get(String topic, int qos);

    int size();

}
