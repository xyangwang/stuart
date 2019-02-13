package io.stuart.entities.metrics;

import java.io.Serializable;
import java.util.concurrent.atomic.LongAdder;

import lombok.Getter;
import lombok.ToString;

@ToString
public class NodeMetrics implements Serializable {

    private static final long serialVersionUID = 3853125081633439170L;

    private static volatile NodeMetrics instance;

    @Getter
    private LongAdder connCount;

    @Getter
    private LongAdder connMax;

    @Getter
    private LongAdder topicCount;

    @Getter
    private LongAdder topicMax;

    @Getter
    private LongAdder sessCount;

    @Getter
    private LongAdder sessMax;

    @Getter
    private LongAdder subCount;

    @Getter
    private LongAdder subMax;

    private NodeMetrics() {
        this.connCount = new LongAdder();
        this.connMax = new LongAdder();
        this.topicCount = new LongAdder();
        this.topicMax = new LongAdder();
        this.sessCount = new LongAdder();
        this.sessMax = new LongAdder();
        this.subCount = new LongAdder();
        this.subMax = new LongAdder();
    }

    public static NodeMetrics getInstance() {
        if (instance == null) {
            synchronized (NodeMetrics.class) {
                if (instance == null) {
                    instance = new NodeMetrics();
                }
            }
        }

        return instance;
    }

}
