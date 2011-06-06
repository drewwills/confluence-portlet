package edu.illinois.my.wiki.portlet.action.result;

import org.apache.log4j.Logger;

import edu.illinois.my.helpers.ExposingBindModule;
import edu.illinois.my.wiki.portlet.WikiPortlet;
import edu.illinois.my.wiki.portlet.error.ErrorMessages;
import edu.illinois.my.wiki.services.wrappers.Log4jWrapper;
import edu.illinois.my.wiki.services.wrappers.LoggerInterface;

public final class ResultWiring extends ExposingBindModule {
    private final ErrorMessages errorMessages;

    public ResultWiring(ErrorMessages errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    protected void configure() {
        bind(ErrorMessages.class).toInstance(errorMessages);
        bind(LoggerInterface.class).toInstance(wrappedLog4j());
        exposingBind(ActionResultFactory.class, ActionResultFactoryImpl.class);
    }

    private LoggerInterface wrappedLog4j() {
        return new Log4jWrapper(Logger.getLogger(WikiPortlet.class));
    }
}
