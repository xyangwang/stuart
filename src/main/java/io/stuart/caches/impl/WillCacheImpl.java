package io.stuart.caches.impl;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.WillCache;
import io.stuart.entities.cache.MqttWillMessage;
import io.stuart.utils.IdUtil;

public class WillCacheImpl implements WillCache {

    private final IgniteCache<String, MqttWillMessage> cache;

    public WillCacheImpl(Ignite ignite, CacheConfiguration<String, MqttWillMessage> cfg) {
        // get or create cache
        this.cache = ignite.getOrCreateCache(cfg);
    }

    @Override
    public void save(MqttWillMessage message) {
        if (message == null || !IdUtil.validateClientId(message.getClientId())) {
            return;
        }

        cache.put(message.getClientId(), message);
    }

    @Override
    public boolean delete(String clientId) {
        if (!IdUtil.validateClientId(clientId)) {
            return false;
        }

        return cache.remove(clientId);
    }

    @Override
    public MqttWillMessage get(String clientId) {
        if (!IdUtil.validateClientId(clientId)) {
            return null;
        }

        return cache.get(clientId);
    }

}
