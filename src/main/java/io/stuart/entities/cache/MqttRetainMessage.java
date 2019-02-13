package io.stuart.entities.cache;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttRetainMessage implements Serializable {

    private static final long serialVersionUID = -8150188768311514785L;

    private String topic;

    private byte[] payload;

    private int qos;

    private boolean dup;

}
