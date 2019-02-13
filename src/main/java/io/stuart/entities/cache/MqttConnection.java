package io.stuart.entities.cache;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;
import lombok.ToString;

/**
 * listener => address:port <BR/>
 * listener => MqttListener.addressAndPort
 */
@Data
@ToString
public class MqttConnection implements Serializable {

    private static final long serialVersionUID = -4529461224698101060L;

    @QuerySqlField(index = true, inlineSize = -1)
    private String clientId;

    private String username;

    private String ipAddr;

    private int port;

    private boolean cleanSession;

    private int protocolVersion;

    private int keepAliveTime;

    @QuerySqlField
    private long connectTime;

    @QuerySqlField
    private String listener;

}
