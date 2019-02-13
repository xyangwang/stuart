package io.stuart.verticles.mqtt.impl;

import java.util.concurrent.atomic.AtomicInteger;

import io.stuart.config.Config;
import io.stuart.consts.SysConst;
import io.stuart.log.Logger;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.mqtt.MqttServerOptions;

public class StdSslMqttVerticleImpl extends StdAbstractMqttVerticle {

    // initialize connection count
    private static final AtomicInteger sslConnCount = new AtomicInteger(0);

    @Override
    public MqttServerOptions initOptions() {
        Logger.log().info("Stuart initialize standalone mqtt server options for SSL protocol.");

        // set protocol
        protocol = SysConst.MQTT + SysConst.COLON + SysConst.SSL_PROTOCOL;
        // set port
        port = Config.getMqttSslPort();
        // set listener
        listener = Config.getInstanceListenAddr() + SysConst.COLON + Config.getMqttSslPort();
        // set max connections limit
        connMaxLimit = Config.getMqttSslMaxConns();

        // initialize mqtt server options
        MqttServerOptions options = new MqttServerOptions();

        // set mqtt server options
        options.setHost(Config.getInstanceListenAddr());
        options.setPort(Config.getMqttSslPort());
        options.setMaxMessageSize(Config.getMqttMessageMaxPayload());
        options.setTimeoutOnConnect(Config.getMqttClientConnectTimeoutS());
        options.setKeyCertOptions(new PemKeyCertOptions().setKeyPath(Config.getMqttSslKeyPath()).setCertPath(Config.getMqttSslCertPath()));
        options.setSsl(true);

        // return mqtt server options
        return options;
    }

    @Override
    public boolean limited() {
        if (sslConnCount.get() >= connMaxLimit) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int incrementAndGetConnCount() {
        return sslConnCount.incrementAndGet();
    }

    @Override
    public int decrementAndGetConnCount() {
        return sslConnCount.decrementAndGet();
    }

    @Override
    public int getConnCount() {
        return sslConnCount.get();
    }

}
