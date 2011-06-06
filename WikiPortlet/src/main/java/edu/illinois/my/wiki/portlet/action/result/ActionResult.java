package edu.illinois.my.wiki.portlet.action.result;

import javax.portlet.RenderRequest;

public interface ActionResult {
    String sendToJsp(RenderRequest request);

    boolean isFirstVisit();
}
