package io.stuart.entities.cache;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttTrieKey implements Serializable {

    private static final long serialVersionUID = -5751268169138371915L;

    private String parent;

    private String word;

    public MqttTrieKey() {
        // do nothing...
    }

    public MqttTrieKey(String parent, String word) {
        this.parent = parent;
        this.word = word;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MqttTrieKey other = (MqttTrieKey) obj;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        if (word == null) {
            if (other.word != null)
                return false;
        } else if (!word.equals(other.word))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        return result;
    }

}
