/*
 * Copyright 2019 Yang Wang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
