package io.stuart.consts;

import io.netty.handler.codec.mqtt.MqttMessageType;

public interface MsgConst {

    static final int MAX_MESSAGE_ID = 65535;

    static final int PUB_STATUS = MqttMessageType.PUBLISH.value();

    static final int PUBREC_STATUS = MqttMessageType.PUBREC.value();;

    static final int NULL_ERROR = -100;

    static final int PUBLISH_ERROR = -99;

    static final int ENQUEUE_FAILED = -2;

    static final int ENQUEUE_SUCCEEDED = -1;

}
