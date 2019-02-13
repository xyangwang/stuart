package io.stuart.entities.cache;

import java.io.Serializable;
import java.util.UUID;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttRouter implements Serializable {

    private static final long serialVersionUID = 564608978232733122L;

    @QuerySqlField
    private UUID nodeId;

    @QuerySqlField(index = true, inlineSize = -1)
    private String clientId;

    @QuerySqlField(index = true, inlineSize = -1)
    private String topic;

    @QuerySqlField
    private int qos;

}
