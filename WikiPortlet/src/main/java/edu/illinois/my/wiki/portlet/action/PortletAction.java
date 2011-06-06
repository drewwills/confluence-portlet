package edu.illinois.my.wiki.portlet.action;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.Parameters;

public interface PortletAction<W extends WikiService<?>> {
    ActionResult execute(Parameters renderParameters, Username username);
}
