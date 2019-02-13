package io.stuart.caches;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.NodeCacheImpl;
import io.stuart.entities.cache.MqttNode;
import io.stuart.enums.Status;

public interface NodeCache {

    static NodeCache create(Ignite ignite, CacheConfiguration<UUID, MqttNode> cfg) {
        return new NodeCacheImpl(ignite, cfg);
    }

    boolean saveIfAbsent(MqttNode node);

    void clear();

    boolean delete(UUID nodeId);

    void update(MqttNode node);

    void update(UUID nodeId, Status status);

    boolean contains(UUID nodeId);

    MqttNode get(UUID nodeId);

    MqttNode get(String instanceId, String listenAddr);

    Lock lock(UUID nodeId);

    List<MqttNode> query(Status status);

}
