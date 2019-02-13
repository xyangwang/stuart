package io.stuart.entities.cache;

import java.io.Serializable;
import java.util.UUID;

import org.apache.ignite.cache.query.annotations.QueryGroupIndex;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import lombok.Data;
import lombok.ToString;

/**
 * listenAddr => Config.getInstanceListenAddr()
 */
@Data
@ToString
@QueryGroupIndex(name = "mqtt_node_idx", inlineSize = -1)
public class MqttNode implements Serializable {

    private static final long serialVersionUID = 1464462821907068248L;

    private UUID nodeId;

    @QuerySqlField(groups = { "mqtt_node_idx" })
    private String instanceId;

    @QuerySqlField(groups = { "mqtt_node_idx" })
    private String listenAddr;

    private String version;

    private boolean localAuth;

    private String javaVersion;

    @QuerySqlField(index = true, inlineSize = -1)
    private int status;

    private String thread;

    private String cpu;

    private String heap;

    private String offHeap;

    private String maxFileDescriptors;

}
