package io.stuart.entities.internal;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttRoute implements Serializable {

    private static final long serialVersionUID = -953459924280313158L;

    private UUID nodeId;

    private String clientId;

    private int qos;

}
