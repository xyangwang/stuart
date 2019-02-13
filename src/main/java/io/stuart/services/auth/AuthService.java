package io.stuart.services.auth;

import java.util.List;
import java.util.function.Function;

import io.stuart.entities.internal.MqttAuthority;
import io.stuart.services.PowerService;

public interface AuthService extends PowerService {

    void auth(String username, String password, Function<Boolean, Void> handler);

    void access(String username, String ipAddr, String clientId, final List<MqttAuthority> auth, Function<List<MqttAuthority>, Void> handler);

    void access(String username, String ipAddr, String clientId, MqttAuthority auth, Function<MqttAuthority, Void> handler);

}
