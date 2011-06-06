package edu.illinois.my.wiki.portlet;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.PortletAction;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.services.parameters.Parameters;

public interface PortletActionExecutor {
    ActionResult processAction(Parameters parameters, Username username,
            PortletAction<?> action);
}
