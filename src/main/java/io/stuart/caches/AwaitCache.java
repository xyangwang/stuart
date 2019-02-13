package io.stuart.caches;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteSet;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.CollectionConfiguration;

import io.stuart.caches.impl.AwaitCacheImpl;
import io.stuart.entities.cache.MqttAwaitMessage;
import io.stuart.entities.cache.MqttAwaitMessageKey;
import io.stuart.ext.collections.BoundedIgniteMap;

public interface AwaitCache {

    static AwaitCache create(Ignite ignite, CacheConfiguration<MqttAwaitMessageKey, MqttAwaitMessage> cacheCfg, CollectionConfiguration setCfg) {
        return new AwaitCacheImpl(ignite, cacheCfg, setCfg);
    }

    BoundedIgniteMap<MqttAwaitMessageKey, MqttAwaitMessage> open(String clientId);

    void close(String clientId);

    IgniteSet<MqttAwaitMessageKey> set(String clientId, boolean create);

}
