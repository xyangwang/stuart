package io.stuart.services.metrics;

import io.stuart.services.PowerService;
import io.stuart.services.metrics.impl.MetricsServiceImpl;

public interface MetricsService extends PowerService {

    static MetricsService i() {
        return MetricsServiceImpl.getInstance();
    }

    boolean isEnabled();

    void grecord(String clientId, long point, long... values);

    void grecord(long point, long... values);

    void record(String clientId, long... pairs);

    void record(long... pairs);

    long getRetainCount();

    long getRetainMax();

}
