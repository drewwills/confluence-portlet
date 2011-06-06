package edu.illinois.my.wiki.portlet.action;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.error.ErrorKeys;
import edu.illinois.my.wiki.services.parameters.Parameters;

final class PortalErrorAction implements PortletAction<UnknownService> {
    private final String logMessage;
    private final ActionResultFactory resultFactory;

    PortalErrorAction(String logMessage, ActionResultFactory resultFactory) {
        this.logMessage = logMessage;
        this.resultFactory = resultFactory;
    }

    @Override
    public ActionResult execute(Parameters renderParameters, Username username) {
        return resultFactory.createLoggedError(ErrorKeys.PORTAL, logMessage);
    }
}
