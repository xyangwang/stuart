package io.stuart.entities.internal.codec;

import io.stuart.entities.internal.MqttMessageTuple;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;

public class MqttMessageTupleCodec implements MessageCodec<MqttMessageTuple, MqttMessageTuple> {

    @Override
    public void encodeToWire(Buffer buffer, MqttMessageTuple tuple) {
        // get json object
        JsonObject jsonToEncode = JsonObject.mapFrom(tuple);
        // get json string
        String jsonToStr = jsonToEncode.encode();
        // get json string length
        int length = jsonToStr.getBytes().length;

        // append json string length
        buffer.appendInt(length);
        // append json string
        buffer.appendString(jsonToStr);
    }

    @Override
    public MqttMessageTuple decodeFromWire(int pos, Buffer buffer) {
        // get position
        int position = pos;

        // get json string length
        int length = buffer.getInt(position);
        // get json string
        String jsonStr = buffer.getString(position += 4, position += length);
        // get json object
        JsonObject contentJson = new JsonObject(jsonStr);

        return contentJson.mapTo(MqttMessageTuple.class);
    }

    @Override
    public MqttMessageTuple transform(MqttMessageTuple tuple) {
        return tuple;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }

}
