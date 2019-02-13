package io.stuart.entities.cache;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;
import lombok.ToString;

/**
 * protocol => AbstractMqttVerticle.protocol <BR/>
 * addressAndPort => AbstractMqttVerticle.listener
 */
@Data
@ToString
public class MqttListener implements Serializable {

    private static final long serialVersionUID = 8907305770392418501L;

    @QuerySqlField
    private String protocol;

    @QuerySqlField
    private String addressAndPort;

    private int connMaxLimit;

    private int connCount;

}
