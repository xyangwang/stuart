package io.stuart.entities.metrics;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SessionMetrics implements Serializable {

    private static final long serialVersionUID = -2629306793850959422L;

    private long topicCount;

    private long awaitSize;

    private long queueSize;

    private long inflightSize;

    private long droppedCount;

    private long sentCount;

    private long enqueueCount;

}
