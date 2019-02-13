package io.stuart.caches;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

import io.stuart.caches.impl.UserCacheImpl;
import io.stuart.entities.auth.MqttUser;

public interface UserCache {

    static UserCache create(Ignite ignite, CacheConfiguration<String, MqttUser> cfg, AdminCache adminCache) {
        return new UserCacheImpl(ignite, cfg, adminCache);
    }

    int add(MqttUser user);

    int delete(String username);

    int update(MqttUser user);

    int update(MqttUser user, String adminAccount, String adminPasswd);

    MqttUser get(String username);

    int count(String username);

    List<MqttUser> query(String username, Integer pageNum, Integer pageSize);

}
