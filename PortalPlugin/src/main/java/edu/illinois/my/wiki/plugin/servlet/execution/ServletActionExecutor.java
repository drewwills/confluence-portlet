package edu.illinois.my.wiki.plugin.servlet.execution;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import edu.illinois.my.wiki.plugin.servlet.action.ServletAction;
import edu.illinois.my.wiki.services.parameters.Parameters;

public interface ServletActionExecutor {
    void manageExecution(Parameters requestParameters, String ip,
            ServletAction<?> action, HttpServletResponse httpResponse)
            throws IOException;
}
