package io.stuart.services.session;

import io.stuart.services.PowerService;
import io.stuart.sessions.SessionWrapper;
import io.vertx.mqtt.MqttEndpoint;

public interface SessionService extends PowerService {

    void openSession(MqttEndpoint endpoint);

    void closeSession(MqttEndpoint endpoint);

    void putWrapper(String clientId, SessionWrapper wrapper);

    SessionWrapper removeWrapper(String clientId);

    SessionWrapper getWrapper(String clientId);

    void saveSession(String clientId, boolean cleanSession);

    boolean deleteSession(String clientId);

}
