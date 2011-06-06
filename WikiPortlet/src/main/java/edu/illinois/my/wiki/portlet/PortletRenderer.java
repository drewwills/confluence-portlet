package edu.illinois.my.wiki.portlet;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import edu.illinois.my.wiki.portlet.action.result.ActionResult;

public interface PortletRenderer {
    void doView(ActionResult result, RenderRequest request,
            RenderResponse response) throws PortletException, IOException;

    void doHelp(RenderRequest request, RenderResponse response)
            throws PortletException, IOException;
}
