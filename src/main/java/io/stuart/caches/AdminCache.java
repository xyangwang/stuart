package io.stuart.caches;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.AdminCacheImpl;
import io.stuart.entities.auth.MqttAdmin;

public interface AdminCache {

    static AdminCache create(Ignite ignite, CacheConfiguration<String, MqttAdmin> cfg) {
        return new AdminCacheImpl(ignite, cfg);
    }

    void init();

    int add(MqttAdmin admin);

    int delete(String account);

    int update(MqttAdmin admin);

    int update(MqttAdmin admin, String oldPasswd, String newPasswd);

    MqttAdmin get(String account);

    int count(String account);

    List<MqttAdmin> query(String account, Integer pageNum, Integer pageSize);

}
