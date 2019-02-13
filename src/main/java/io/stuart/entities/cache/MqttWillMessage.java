package io.stuart.entities.cache;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttWillMessage implements Serializable {

    private static final long serialVersionUID = 2173961158751954698L;

    private String clientId;

    private String topic;

    private String payload;

    private int qos;

    private boolean retain;

}
