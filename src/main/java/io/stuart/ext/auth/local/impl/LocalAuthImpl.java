package io.stuart.ext.auth.local.impl;

import io.stuart.entities.auth.MqttAdmin;
import io.stuart.ext.auth.local.LocalAuth;
import io.stuart.services.cache.CacheService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;

public class LocalAuthImpl implements AuthProvider, LocalAuth {

    private CacheService cacheService;

    public LocalAuthImpl(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public void authenticate(JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {
        String username = authInfo.getString("username");

        if (username == null) {
            resultHandler.handle(Future.failedFuture("authInfo must contain username in 'username' field"));
            return;
        }

        String password = authInfo.getString("password");

        if (password == null) {
            resultHandler.handle(Future.failedFuture("authInfo must contain password in 'password' field"));
            return;
        }

        MqttAdmin admin = new MqttAdmin();
        admin.setAccount(username);
        admin.setPassword(password);

        if (cacheService.login(admin)) {
            resultHandler.handle(Future.succeededFuture(new LocalUser(this, username)));
        } else {
            resultHandler.handle(Future.failedFuture("no such username, or password incorrect."));
        }
    }

}
