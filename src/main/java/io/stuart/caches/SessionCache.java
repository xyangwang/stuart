package io.stuart.caches;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.SessionCacheImpl;
import io.stuart.entities.cache.MqttSession;

public interface SessionCache {

    static SessionCache create(Ignite ignite, CacheConfiguration<String, MqttSession> cfg) {
        return new SessionCacheImpl(ignite, cfg);
    }

    int save(MqttSession session);

    boolean delete(String clientId);

    boolean contains(String clientId);

    MqttSession get(String clientId);

    List<MqttSession> get(UUID nodeId, boolean cleanSession);

    Lock lock(String clientId);

    int count(UUID nodeId, String clientId);

    List<MqttSession> query(UUID nodeId, String clientId, Integer pageNum, Integer pageSize);

}
