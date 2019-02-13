package io.stuart.closures;

import org.apache.ignite.lang.IgniteClosure;

import io.stuart.entities.internal.MqttConnections;
import io.stuart.entities.param.QueryConnections;
import io.stuart.services.cache.CacheService;

public class QueryConnectionsClosure implements IgniteClosure<QueryConnections, MqttConnections> {

    private static final long serialVersionUID = 278244255482949257L;

    private CacheService cacheService;

    public QueryConnectionsClosure(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public MqttConnections apply(QueryConnections qcp) {
        String clientId = qcp.getClientId();
        int pageNum = qcp.getPageNum();
        int pageSize = qcp.getPageSize();

        MqttConnections conns = new MqttConnections();
        conns.setTotal(cacheService.countConnections(clientId));
        conns.setConnections(cacheService.getConnections(clientId, pageNum, pageSize));

        return conns;
    }

}
