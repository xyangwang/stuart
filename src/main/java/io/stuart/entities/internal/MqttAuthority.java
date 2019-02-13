package io.stuart.entities.internal;

import java.io.Serializable;

import io.stuart.enums.Access;
import io.stuart.enums.Authority;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttAuthority implements Serializable {

    private static final long serialVersionUID = -5135604764912692767L;

    private String topic;

    private int qos;

    private Access access;

    private Authority authority;

}
