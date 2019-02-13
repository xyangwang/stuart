package io.stuart.closures;

import java.util.concurrent.locks.Lock;

import org.apache.ignite.lang.IgniteClosure;

import io.stuart.entities.cache.MqttSession;
import io.stuart.services.cache.CacheService;

public class DestroySessionClosure implements IgniteClosure<MqttSession, Void> {

    private static final long serialVersionUID = 3682390996308034473L;

    private CacheService cacheService;

    public DestroySessionClosure(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public Void apply(MqttSession session) {
        if (session == null) {
            return null;
        }

        // get client id
        String clientId = session.getClientId();
        // get session lock
        Lock lock = cacheService.getSessionLock(clientId);

        // try to lock it
        if (lock.tryLock()) {
            try {
                // get current session information
                MqttSession current = cacheService.getSession(clientId);

                if (current == null || (current.getNodeId().equals(session.getNodeId()) && current.getCreateTime() == session.getCreateTime())) {
                    if (session.isCleanSession()) {
                        // destroy transient session
                        cacheService.destroyTransientSession(clientId);
                    } else {
                        // destroy persistent session
                        cacheService.destroyPersistentSession(clientId);
                    }

                    // delete session
                    cacheService.deleteSession(clientId);
                }
            } finally {
                // unlock it
                lock.unlock();
            }
        }

        return null;
    }

}
