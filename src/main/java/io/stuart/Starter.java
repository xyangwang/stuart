package io.stuart;

import io.stuart.config.Config;
import io.stuart.consts.ParamConst;
import io.stuart.consts.PropConst;
import io.stuart.hook.ShutdownHook;
import io.stuart.log.Logger;
import io.stuart.services.verticle.VerticleService;
import io.stuart.services.verticle.impl.ClsVerticleServiceImpl;
import io.stuart.services.verticle.impl.StdVerticleServiceImpl;
import io.stuart.utils.CmdUtil;
import io.vertx.core.cli.CommandLine;

public class Starter {

    public static void main(String[] args) {
        CommandLine commandLine = CmdUtil.cli(args);

        if (commandLine == null) {
            System.exit(-1);
        }

        // initialize server configurations
        Config.init(commandLine);
        // set server log directory system property
        System.setProperty(PropConst.LOG_DIR, Config.getLogDir());
        // set server log level system property
        System.setProperty(PropConst.LOG_LEVEL, Config.getLogLevel());

        // get cluster mode
        String clusterMode = Config.getClusterMode();
        // verticle service
        VerticleService verticleService = null;

        if (ParamConst.CLUSTER_MODE_STD.equalsIgnoreCase(clusterMode)) {
            // new standalone verticle service
            verticleService = new StdVerticleServiceImpl();
        } else if (ParamConst.CLUSTER_MODE_VMIP.equalsIgnoreCase(clusterMode) || ParamConst.CLUSTER_MODE_ZK.equalsIgnoreCase(clusterMode)) {
            // new clustered verticle service
            verticleService = new ClsVerticleServiceImpl();
        }

        // check: verticle service is not null
        if (verticleService != null) {
            Logger.log().info("Stuart server {} is starting...", Starter.class.getPackage().getImplementationVersion());

            // start verticle service
            verticleService.start();

            // add shutdown hook
            Runtime.getRuntime().addShutdownHook(new ShutdownHook(verticleService));
        } else {
            Logger.log().error("Stuart's server cluster mode option is invalid.");
        }
    }

}
