package edu.illinois.my.wiki.plugin.servlet.execution.services.impl;

import org.apache.log4j.Logger;

import edu.illinois.my.helpers.ExposingBindModule;
import edu.illinois.my.wiki.plugin.servlet.action.ServletAction;
import edu.illinois.my.wiki.plugin.servlet.execution.services.ExceptionLogger;
import edu.illinois.my.wiki.plugin.servlet.execution.services.UserLookup;
import edu.illinois.my.wiki.plugin.servlet.execution.services.Validator;
import edu.illinois.my.wiki.services.wrappers.Log4jWrapper;
import edu.illinois.my.wiki.services.wrappers.LoggerInterface;

public final class ExecutionServicesWiring extends ExposingBindModule {
    public ExecutionServicesWiring() {}

    @Override
    protected void configure() {
        exposingBind(Validator.class, RequestValidatorWrapper.class);

        bind(LoggerInterface.class).toInstance(wrappedLog4j());
        exposingBind(ExceptionLogger.class, ExceptionLoggerImpl.class);

        exposingBind(UserLookup.class, UserAccessorWrapper.class);
    }

    private Log4jWrapper wrappedLog4j() {
        Logger logger = Logger.getLogger(ServletAction.class);
        return new Log4jWrapper(logger);
    }
}
