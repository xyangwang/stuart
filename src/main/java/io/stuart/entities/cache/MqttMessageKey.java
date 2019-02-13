package io.stuart.entities.cache;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttMessageKey implements Serializable {

    private static final long serialVersionUID = 8666901596591507691L;

    private String clientId;

    private int messageId;

    public MqttMessageKey() {
        // do nothing...
    }

    public MqttMessageKey(String clientId, int messageId) {
        this.clientId = clientId;
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MqttMessageKey other = (MqttMessageKey) obj;
        if (clientId == null) {
            if (other.clientId != null)
                return false;
        } else if (!clientId.equals(other.clientId))
            return false;
        if (messageId != other.messageId)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
        result = prime * result + messageId;
        return result;
    }

}
