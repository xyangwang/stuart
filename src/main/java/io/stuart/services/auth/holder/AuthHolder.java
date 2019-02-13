package io.stuart.services.auth.holder;

import io.stuart.config.Config;
import io.stuart.consts.ParamConst;
import io.stuart.services.auth.AuthService;
import io.stuart.services.auth.impl.LocalAuthServiceImpl;
import io.stuart.services.auth.impl.MongoDBAuthServiceImpl;
import io.stuart.services.auth.impl.MySQLAuthServiceImpl;
import io.stuart.services.auth.impl.PostgreSQLAuthServiceImpl;
import io.stuart.services.auth.impl.RedisAuthServiceImpl;
import io.stuart.services.cache.CacheService;
import io.vertx.core.Vertx;

public class AuthHolder {

    private static volatile AuthService authService;

    public static AuthService getAuthService(Vertx vertx, CacheService cacheService) {
        if (authService == null) {
            synchronized (AuthHolder.class) {
                if (authService == null) {
                    // get authentication mode
                    String authMode = Config.getAuthMode();

                    if (ParamConst.AUTH_MODE_REDIS.equalsIgnoreCase(authMode)) {
                        // new redis authentication and authorization service
                        authService = new RedisAuthServiceImpl(vertx);
                    } else if (ParamConst.AUTH_MODE_MYSQL.equalsIgnoreCase(authMode)) {
                        // new mysql authentication and authorization service
                        authService = new MySQLAuthServiceImpl(vertx);
                    } else if (ParamConst.AUTH_MODE_POSTGRE.equalsIgnoreCase(authMode)) {
                        // new postgresql authentication and authorization service
                        authService = new PostgreSQLAuthServiceImpl(vertx);
                    } else if (ParamConst.AUTH_MODE_MONGO.equalsIgnoreCase(authMode)) {
                        // new mongodb authentication and authorization service
                        authService = new MongoDBAuthServiceImpl(vertx);
                    } else if (ParamConst.AUTH_MODE_LOCAL.equalsIgnoreCase(authMode)) {
                        // new local authentication and authorization service
                        authService = new LocalAuthServiceImpl(cacheService);
                    } else {
                        // set authentication and authorization service = null
                        authService = null;
                    }
                }
            }
        }

        return authService;
    }

}
