package io.stuart.services.verticle.impl;

import java.util.Timer;

import io.stuart.config.Config;
import io.stuart.log.Logger;
import io.stuart.services.auth.AuthService;
import io.stuart.services.auth.holder.AuthHolder;
import io.stuart.services.cache.CacheService;
import io.stuart.services.cache.impl.StdCacheServiceImpl;
import io.stuart.services.metrics.MetricsService;
import io.stuart.services.session.SessionService;
import io.stuart.services.session.impl.StdSessionServiceImpl;
import io.stuart.services.verticle.VerticleService;
import io.stuart.tasks.SysRuntimeInfoTask;
import io.stuart.utils.VertxUtil;
import io.stuart.verticles.mqtt.impl.StdSslMqttVerticleImpl;
import io.stuart.verticles.mqtt.impl.StdTcpMqttVerticleImpl;
import io.stuart.verticles.web.impl.WebVerticleImpl;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class StdVerticleServiceImpl implements VerticleService {

    private Vertx vertx;

    private CacheService cacheService;

    private SessionService sessionService;

    private AuthService authService;

    private MetricsService metricsService;

    private Timer timer = new Timer();

    @Override
    public void start() {
        Logger.log().info("Stuart's standalone instance is starting...");

        // vert.x options
        VertxOptions vertxOptions = VertxUtil.vertxOptions();
        // vertx. deployment options
        DeploymentOptions deploymentOptions = VertxUtil.vertxDeploymentOptions(vertxOptions, null);

        // get standalone cache service
        cacheService = StdCacheServiceImpl.getInstance();
        // start standalone cache service
        cacheService.start();

        // start vert.x instance
        vertx = Vertx.vertx(vertxOptions);

        // get standalone session service
        sessionService = StdSessionServiceImpl.getInstance(vertx, cacheService);
        // start standalone session service
        sessionService.start();

        // get authentication and authorization service
        authService = AuthHolder.getAuthService(vertx, cacheService);

        if (authService != null) {
            // start authentication and authorization service
            authService.start();
        }

        // create metrics service
        metricsService = MetricsService.i();
        // start metrics service
        metricsService.start();

        // deploy the standalone tcp mqtt verticle
        vertx.deployVerticle(StdTcpMqttVerticleImpl.class.getName(), deploymentOptions);

        // if enable mqtt ssl protocol
        if (Config.isMqttSslEnable()) {
            // deploy the standalone ssl mqtt verticle
            vertx.deployVerticle(StdSslMqttVerticleImpl.class.getName(), deploymentOptions);
        }

        // deploy the web verticle
        vertx.deployVerticle(new WebVerticleImpl(vertx, cacheService));

        // set scheduled task
        timer.schedule(new SysRuntimeInfoTask(cacheService), 0, Config.getInstanceMetricsPeriodMs());
    }

    @Override
    public void stop() {
        if (vertx != null) {
            vertx.deploymentIDs().forEach(id -> {
                vertx.undeploy(id);
            });
        }

        if (metricsService != null) {
            metricsService.stop();
        }

        if (authService != null) {
            authService.stop();
        }

        if (sessionService != null) {
            sessionService.stop();
        }

        if (cacheService != null) {
            cacheService.stop();
        }
    }

}
