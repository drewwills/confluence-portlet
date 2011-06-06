package edu.illinois.my.wiki.plugin.servlet.execution;

import edu.illinois.my.helpers.ExposingBindModule;
import edu.illinois.my.wiki.plugin.servlet.execution.services.impl.ExecutionServicesWiring;

public final class ExecutionWiring extends ExposingBindModule {
    public ExecutionWiring() {}

    @Override
    protected void configure() {
        install(new ExecutionServicesWiring());

        exposingBind(ServletActionExecutor.class, ValidatingExecutor.class);
    }
}
