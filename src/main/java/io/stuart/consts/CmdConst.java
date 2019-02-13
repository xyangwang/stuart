package io.stuart.consts;

public interface CmdConst {

    static final String CLI_SHELL = "java -jar <stuart>-<version>-fat.jar";

    static final String CLI_SUMMARY = "Stuart: MQTT Server.";

    static final String CFG_L_NAME = "cfg";

    static final String CFG_S_NAME = "c";

    static final String CFG_DESC = "The config file (in properties format).";

    static final String INSTANCE_ID_L_NAME = "instance-id";

    static final String INSTANCE_ID_S_NAME = "i";

    static final String INSTANCE_ID_DESC = "The instance identity. If server is in cluster mode, it must be unique.";

    static final String LISTEN_ADDR_L_NAME = "listen-address";

    static final String LISTEN_ADDR_S_NAME = "l";

    static final String LISTEN_ADDR_DESC = "The listen address.";

    static final String STORAGE_PATH_L_NAME = "storage-path";

    static final String STORAGE_PATH_S_NAME = "s";

    static final String STORAGE_PATH_DESC = "The local storage path.";

    static final String MQTT_PORT_L_NAME = "mqtt-port";

    static final String MQTT_PORT_S_NAME = "m";

    static final String MQTT_PORT_DESC = "The mqtt listen port (default value is 1883).";

    static final String MQTT_SSL_PORT_L_NAME = "mqtt-ssl-port";

    static final String MQTT_SSL_PORT_S_NAME = "M";

    static final String MQTT_SSL_PORT_DESC = "The mqtt ssl listen port (default value is 8883).";

    static final String WS_PORT_L_NAME = "ws-port";

    static final String WS_PORT_S_NAME = "w";

    static final String WS_PORT_DESC = "The websocket listen port (default value is 8080).";

    static final String WSS_PORT_L_NAME = "wss-port";

    static final String WSS_PORT_S_NAME = "W";

    static final String WSS_PORT_DESC = "The websocket listen port (default value is 8083).";

    static final String HTTP_PORT_L_NAME = "http-port";

    static final String HTTP_PORT_S_NAME = "h";

    static final String HTTP_PORT_DESC = "The http listen port (defalt value is 18083).";

}
