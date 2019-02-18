package io.stuart.cli;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.UsageMessageFormatter;
import io.vertx.core.cli.impl.DefaultCLI;

public class StuartCLI extends DefaultCLI {

    private static final int USAGE_WIDTH = 120;

    @Override
    public CLI usage(StringBuilder builder) {
        UsageMessageFormatter formatter = new UsageMessageFormatter();
        formatter.setWidth(USAGE_WIDTH);
        formatter.usage(builder, this);
        return this;
    }

    @Override
    public CLI usage(StringBuilder builder, String prefix) {
        UsageMessageFormatter formatter = new UsageMessageFormatter();
        formatter.setWidth(USAGE_WIDTH);
        formatter.usage(builder, prefix, this);
        return this;
    }

}
