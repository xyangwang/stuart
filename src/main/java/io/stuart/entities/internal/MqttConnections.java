package io.stuart.entities.internal;

import java.io.Serializable;
import java.util.List;

import io.stuart.entities.cache.MqttConnection;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttConnections implements Serializable {

    private static final long serialVersionUID = -7744483425215150918L;

    private int total;

    private List<MqttConnection> connections;

}
