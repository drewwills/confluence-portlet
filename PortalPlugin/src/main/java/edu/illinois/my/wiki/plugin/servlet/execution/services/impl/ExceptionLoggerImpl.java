package edu.illinois.my.wiki.plugin.servlet.execution.services.impl;

import com.google.inject.Inject;

import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionException;
import edu.illinois.my.wiki.plugin.servlet.execution.services.ExceptionLogger;
import edu.illinois.my.wiki.services.parameters.Parameters;
import edu.illinois.my.wiki.services.wrappers.LoggerInterface;

class ExceptionLoggerImpl implements ExceptionLogger {
    private final LoggerInterface log;

    private static final String SERVLET_ACTION_EXCEPTION_ERROR =
            "The following internal error occurred.";
    private static final String EXCEPTION_ERROR =
            "The following exception occurred and will be transmitted to the request origin (probably the Portal) for logging there.";

    @Inject
    ExceptionLoggerImpl(LoggerInterface log) {
        this.log = log;
    }

    @Override
    public void logServletAction(Parameters requestParameters, String ip,
            ServletActionException exception) {
        logException(SERVLET_ACTION_EXCEPTION_ERROR, requestParameters, ip,
                exception);
    }

    @Override
    public void logRuntime(Parameters requestParameters, String ip,
            RuntimeException exception) {
        logException(EXCEPTION_ERROR, requestParameters, ip, exception);
    }

    private void logException(String logMessage, Parameters requestParameters,
            String ip, RuntimeException exception) {
        StringBuilder message = new StringBuilder();
        message.append(logMessage);
        message.append(" IP: ").append(ip);
        message.append(" Request Parameters: ").append(requestParameters);
        log.error(message.toString(), exception);
    }
}
