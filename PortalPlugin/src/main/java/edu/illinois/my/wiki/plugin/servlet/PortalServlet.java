package edu.illinois.my.wiki.plugin.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;

import edu.illinois.my.wiki.plugin.servlet.action.ServletAction;
import edu.illinois.my.wiki.plugin.servlet.execution.ServletActionExecutor;
import edu.illinois.my.wiki.services.parameters.Parameters;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;
import edu.illinois.my.wiki.services.parameters.ParametersBuilderImpl;

/**
 * Handles servlet and network responsibilities for <code>ServletAction</code>s.
 * <p>
 * This class is Serializable, and its fields are marked as transient, but
 * hopefully this will never be serialized. Serialization could be fixed by
 * getting the injector each time from the ServletConfig if the injector were
 * null.
 * 
 * @param <S> the ServletAction to be executed
 */
abstract class PortalServlet<S extends ServletAction<?>> extends HttpServlet {
    private transient Provider<S> actionProvider;
    private transient ServletActionExecutor actionExecutor;

    private static final long serialVersionUID = 1L;

    @Inject
    final void setDependencies(Provider<S> actionProvider,
            ServletActionExecutor actionExecutor) {
        this.actionProvider = actionProvider;
        this.actionExecutor = actionExecutor;
    }

    /**
     * This method is called by the servlet container.
     * 
     * @throws ServletException never
     * @throws IOException never
     * @throws RuntimeException when IOException happens
     */
    @Override
    protected final void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Parameters requestParameters = getRequestParameters(req);
        String ip = req.getRemoteAddr();
        S action = actionProvider.get();
        actionExecutor.manageExecution(requestParameters, ip, action, res);
    }

    private Parameters getRequestParameters(HttpServletRequest req) {
        @SuppressWarnings("unchecked")
        Map<String, String[]> parameterMap = req.getParameterMap();
        ParametersBuilder builder = new ParametersBuilderImpl();
        builder.addFirstValues(parameterMap);
        return builder.build();
    }
}
