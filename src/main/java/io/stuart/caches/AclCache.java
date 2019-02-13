package io.stuart.caches;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.AclCacheImpl;
import io.stuart.entities.auth.MqttAcl;

public interface AclCache {

    static AclCache create(Ignite ignite, CacheConfiguration<Long, MqttAcl> cfg) {
        return new AclCacheImpl(ignite, cfg);
    }

    void init();

    long add(MqttAcl acl);

    int delete(Long seq);

    int update(MqttAcl acl);

    int reorder(List<MqttAcl> acls);

    List<MqttAcl> get(String username, String ipAddr, String clientId);

    List<Object[]> query();

}
