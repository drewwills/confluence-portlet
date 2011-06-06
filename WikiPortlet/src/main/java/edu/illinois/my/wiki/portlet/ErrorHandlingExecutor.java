package edu.illinois.my.wiki.portlet;

import com.google.inject.Inject;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.PortletAction;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.error.ErrorKeys;
import edu.illinois.my.wiki.services.parameters.Parameters;

final class ErrorHandlingExecutor implements PortletActionExecutor {
    private final ActionResultFactory resultFactory;

    @Inject
    ErrorHandlingExecutor(ActionResultFactory resultFactory) {
        this.resultFactory = resultFactory;
    }

    @Override
    public ActionResult processAction(Parameters parameters, Username username,
            PortletAction<?> action) {
        if (username.isEmpty()) {
            String logMessage = "getRemoteUser() failed to return a user.";
            return resultFactory.createLoggedError(ErrorKeys.PORTAL, logMessage);
        }
        try {
            return action.execute(parameters, username);
        } catch (RuntimeException exception) {
            return resultFactory.createException(username, exception);
        }
    }
}
