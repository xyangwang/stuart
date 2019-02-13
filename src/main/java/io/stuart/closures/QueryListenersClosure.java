package io.stuart.closures;

import java.util.List;
import java.util.UUID;

import org.apache.ignite.lang.IgniteClosure;

import io.stuart.entities.cache.MqttListener;
import io.stuart.services.cache.CacheService;

public class QueryListenersClosure implements IgniteClosure<UUID, List<MqttListener>> {

    private static final long serialVersionUID = -8379424752790269073L;

    private CacheService cacheService;

    public QueryListenersClosure(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public List<MqttListener> apply(UUID nodeId) {
        return cacheService.getListeners();
    }

}
