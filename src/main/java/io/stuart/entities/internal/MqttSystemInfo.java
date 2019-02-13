package io.stuart.entities.internal;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MqttSystemInfo implements Serializable {

    private static final long serialVersionUID = -8015697294332547618L;

    private String version;

    private String uptime;

    private String systime;

}
