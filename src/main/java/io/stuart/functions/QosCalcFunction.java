package io.stuart.functions;

import org.apache.ignite.cache.query.annotations.QuerySqlFunction;

public class QosCalcFunction {

    @QuerySqlFunction
    public static int downgrade(int messageQos, int topicQos) {
        return messageQos < topicQos ? messageQos : topicQos;
    }

    @QuerySqlFunction
    public static int upgrade(int messageQos, int topicQos) {
        return messageQos > topicQos ? messageQos : topicQos;
    }

}
