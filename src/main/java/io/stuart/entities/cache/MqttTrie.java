package io.stuart.entities.cache;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttTrie implements Serializable {

    private static final long serialVersionUID = 5054599081443730406L;

    private String parent;

    private String word;

    private String self;

    private int count;

}
