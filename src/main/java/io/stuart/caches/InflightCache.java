package io.stuart.caches;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteSet;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.CollectionConfiguration;

import io.stuart.caches.impl.InflightCacheImpl;
import io.stuart.entities.cache.MqttMessage;
import io.stuart.entities.cache.MqttMessageKey;
import io.stuart.ext.collections.BoundedIgniteMapUnsafe;

public interface InflightCache {

    static InflightCache create(Ignite ignite, CacheConfiguration<MqttMessageKey, MqttMessage> cacheCfg, CollectionConfiguration setCfg) {
        return new InflightCacheImpl(ignite, cacheCfg, setCfg);
    }

    BoundedIgniteMapUnsafe<MqttMessageKey, MqttMessage> open(String clientId);

    void close(String clientId);

    IgniteSet<MqttMessageKey> set(String clientId, boolean create);

}
