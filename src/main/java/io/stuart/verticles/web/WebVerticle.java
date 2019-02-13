package io.stuart.verticles.web;

import io.vertx.ext.web.RoutingContext;

public interface WebVerticle {

    void login(RoutingContext rc);

    void logout(RoutingContext rc);

    void initIndex(RoutingContext rc);

    void getSystemInfo(RoutingContext rc);

    void getNodeMetrics(RoutingContext rc);

    void getMqttMetrics(RoutingContext rc);

    void getConnections(RoutingContext rc);

    void getSessions(RoutingContext rc);

    void getTopics(RoutingContext rc);

    void getSubscribes(RoutingContext rc);

    void addUser(RoutingContext rc);

    void deleteUser(RoutingContext rc);

    void updateUser(RoutingContext rc);

    void getUsers(RoutingContext rc);

    void addAcl(RoutingContext rc);

    void deleteAcl(RoutingContext rc);

    void updateAcl(RoutingContext rc);

    void reorderAcls(RoutingContext rc);

    void getAcls(RoutingContext rc);

    void getListeners(RoutingContext rc);

    void addAdmin(RoutingContext rc);

    void deleteAdmin(RoutingContext rc);

    void updateAdmin(RoutingContext rc);

    void getAdmins(RoutingContext rc);

}
