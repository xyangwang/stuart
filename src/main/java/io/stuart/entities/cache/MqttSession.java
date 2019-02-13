package io.stuart.entities.cache;

import java.io.Serializable;
import java.util.UUID;

import org.apache.ignite.cache.query.annotations.QueryGroupIndex;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@QueryGroupIndex(name = "mqtt_session_idx", inlineSize = -1)
public class MqttSession implements Serializable {

    private static final long serialVersionUID = -8008170929831234461L;

    @QuerySqlField(groups = { "mqtt_session_idx" })
    private UUID nodeId;

    @QuerySqlField(groups = { "mqtt_session_idx" })
    private String clientId;

    @QuerySqlField(groups = { "mqtt_session_idx" })
    private boolean cleanSession;

    @QuerySqlField
    private long createTime;

}
