package io.stuart.entities.cache;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttAwaitMessage implements Serializable {

    private static final long serialVersionUID = 8188376972889325843L;

    private String clientId;

    private int messageId;

    private String topic;

    private byte[] payload;

    private int qos;

    private boolean dup;

    private boolean retain;

}
