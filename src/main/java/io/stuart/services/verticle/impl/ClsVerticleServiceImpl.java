package io.stuart.services.verticle.impl;

import java.util.Timer;

import io.stuart.config.Config;
import io.stuart.entities.internal.MqttMessageTuple;
import io.stuart.entities.internal.codec.MqttMessageTupleCodec;
import io.stuart.exceptions.StartException;
import io.stuart.log.Logger;
import io.stuart.services.auth.AuthService;
import io.stuart.services.auth.holder.AuthHolder;
import io.stuart.services.cache.CacheService;
import io.stuart.services.cache.impl.ClsCacheServiceImpl;
import io.stuart.services.metrics.MetricsService;
import io.stuart.services.session.SessionService;
import io.stuart.services.session.impl.ClsSessionServiceImpl;
import io.stuart.services.verticle.VerticleService;
import io.stuart.tasks.SysRuntimeInfoTask;
import io.stuart.utils.VertxUtil;
import io.stuart.verticles.mqtt.impl.ClsSslMqttVerticleImpl;
import io.stuart.verticles.mqtt.impl.ClsTcpMqttVerticleImpl;
import io.stuart.verticles.web.impl.WebVerticleImpl;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.ignite.IgniteClusterManager;

public class ClsVerticleServiceImpl implements VerticleService {

    private Vertx vertx;

    private CacheService cacheService;

    private SessionService sessionService;

    private AuthService authService;

    private MetricsService metricsService;

    private Timer timer = new Timer();

    @Override
    public void start() {
        Logger.log().info("Stuart's clustered instance is starting...");

        // get vert.x options
        VertxOptions vertxOptions = VertxUtil.vertxOptions();
        // get vertx. deployment options
        DeploymentOptions deploymentOptions = VertxUtil.vertxDeploymentOptions(vertxOptions, null);

        // get clustered cache service
        cacheService = ClsCacheServiceImpl.getInstance();
        // start clustered cache service
        cacheService.start();

        // get vert.x cluster manager
        ClusterManager clusterManager = new IgniteClusterManager(cacheService.getIgnite());
        // set vert.x cluster manager
        vertxOptions.setClusterManager(clusterManager);

        Vertx.clusteredVertx(vertxOptions, result -> {
            if (result.succeeded()) {
                // set vert.x instance
                vertx = result.result();
                // set mqtt message tuple codec
                vertx.eventBus().registerDefaultCodec(MqttMessageTuple.class, new MqttMessageTupleCodec());

                // get clustered session service
                sessionService = ClsSessionServiceImpl.getInstance(vertx, cacheService);
                // start clustered session service
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

                // deploy the clustered tcp mqtt verticle
                vertx.deployVerticle(ClsTcpMqttVerticleImpl.class.getName(), deploymentOptions);

                // if enable mqtt ssl protocol
                if (Config.isMqttSslEnable()) {
                    // deploy the clustered ssl mqtt verticle
                    vertx.deployVerticle(ClsSslMqttVerticleImpl.class.getName(), deploymentOptions);
                }

                // deploy the web verticle
                vertx.deployVerticle(new WebVerticleImpl(vertx, cacheService));

                // set scheduled task
                timer.schedule(new SysRuntimeInfoTask(cacheService), 0, Config.getInstanceMetricsPeriodMs());
            } else {
                Logger.log().error("Stuart's clustered vert.x instance start failed, exception: {}.", result.cause());

                throw new StartException(result.cause());
            }
        });
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
