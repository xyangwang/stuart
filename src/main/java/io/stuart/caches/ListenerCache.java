package io.stuart.caches;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.ListenerCacheImpl;
import io.stuart.entities.cache.MqttListener;

public interface ListenerCache {

    static ListenerCache create(Ignite ignite, CacheConfiguration<String, MqttListener> cfg) {
        return new ListenerCacheImpl(ignite, cfg);
    }

    boolean saveIfAbsent(MqttListener listener);

    void clear();

    List<MqttListener> query();

}
