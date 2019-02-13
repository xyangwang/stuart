package io.stuart.caches;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.WillCacheImpl;
import io.stuart.entities.cache.MqttWillMessage;

public interface WillCache {

    static WillCache create(Ignite ignite, CacheConfiguration<String, MqttWillMessage> cfg) {
        return new WillCacheImpl(ignite, cfg);
    }

    void save(MqttWillMessage message);

    boolean delete(String clientId);

    MqttWillMessage get(String clientId);

}
