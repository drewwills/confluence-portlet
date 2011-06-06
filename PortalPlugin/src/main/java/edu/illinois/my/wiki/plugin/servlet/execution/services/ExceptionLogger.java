package edu.illinois.my.wiki.plugin.servlet.execution.services;

import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionException;
import edu.illinois.my.wiki.services.parameters.Parameters;

public interface ExceptionLogger {
    void logServletAction(Parameters requestParameters, String ip,
            ServletActionException servletActionException);

    void logRuntime(Parameters requestParameters, String ip,
            RuntimeException exception);
}
