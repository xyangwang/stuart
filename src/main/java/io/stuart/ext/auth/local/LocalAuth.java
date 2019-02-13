package io.stuart.ext.auth.local;

import io.stuart.ext.auth.local.impl.LocalAuthImpl;
import io.stuart.services.cache.CacheService;
import io.vertx.ext.auth.AuthProvider;

public interface LocalAuth extends AuthProvider {

    static LocalAuth create(CacheService cacheService) {
        return new LocalAuthImpl(cacheService);
    }

}
