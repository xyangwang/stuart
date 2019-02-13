package io.stuart.tasks;

import java.util.TimerTask;

import io.stuart.services.cache.CacheService;

public class SysRuntimeInfoTask extends TimerTask {

    private CacheService cacheService;

    public SysRuntimeInfoTask(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public void run() {
        cacheService.updateNodeSysRuntimeInfo();
    }

}
