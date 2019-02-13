package io.stuart.entities.internal;

import java.io.Serializable;

import io.stuart.entities.cache.MqttMessage;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttMessageTuple implements Serializable {

    private static final long serialVersionUID = 6549707484422956082L;

    private MqttRoute route;

    private MqttMessage message;

    public MqttMessageTuple() {
        // do nothing...
    }

    public MqttMessageTuple(MqttRoute route, MqttMessage message) {
        this.route = route;
        this.message = message;
    }

}
