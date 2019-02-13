package io.stuart.caches;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.ConnectionCacheImpl;
import io.stuart.entities.cache.MqttConnection;

public interface ConnectionCache {

    static ConnectionCache create(Ignite ignite, CacheConfiguration<String, MqttConnection> cfg) {
        return new ConnectionCacheImpl(ignite, cfg);
    }

    void save(MqttConnection conn);

    void clear();

    boolean delete(String clientId);

    boolean contains(String clientId);

    MqttConnection get(String clientId);

    Lock lock(String clientId);

    Map<String, Integer> count();

    int count(String clientId);

    List<MqttConnection> query(String clientId, Integer pageNum, Integer pageSize);

}
