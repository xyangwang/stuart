package io.stuart.entities.cache;

import java.io.Serializable;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.buffer.Buffer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttMessage implements Serializable {

    private static final long serialVersionUID = 8991518961890229180L;

    private String clientId;

    private int messageId;

    private String topic;

    private byte[] payload;

    private int qos;

    private boolean dup;

    private boolean retain;

    private int status;

    private int retry;

    public Buffer publishPayload() {
        return Buffer.buffer(payload);
    }

    public MqttQoS publishQoS() {
        return MqttQoS.valueOf(qos);
    }

}
