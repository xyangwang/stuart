package io.stuart.hook;

import io.stuart.services.verticle.VerticleService;

public class ShutdownHook extends Thread {

    private VerticleService verticleService;

    public ShutdownHook(VerticleService verticleService) {
        this.verticleService = verticleService;
    }

    @Override
    public void run() {
        if (verticleService != null) {
            verticleService.stop();
        }
    }

}
