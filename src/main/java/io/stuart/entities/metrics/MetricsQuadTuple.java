package io.stuart.entities.metrics;

import java.io.Serializable;
import java.util.function.Function;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MetricsQuadTuple implements Serializable {

    private static final long serialVersionUID = 499998216453755534L;

    private Object quota;

    private long value;

    private boolean cresc;

    private Function<MetricsQuadTuple, Boolean> func;

    public MetricsQuadTuple() {
        // do nothing...
    }

    public MetricsQuadTuple(Object quota, long value, boolean cresc) {
        this.quota = quota;
        this.value = value;
        this.cresc = cresc;
    }

    public MetricsQuadTuple(Object quota, long value, boolean cresc, Function<MetricsQuadTuple, Boolean> func) {
        this.quota = quota;
        this.value = value;
        this.cresc = cresc;
        this.func = func;
    }

}
