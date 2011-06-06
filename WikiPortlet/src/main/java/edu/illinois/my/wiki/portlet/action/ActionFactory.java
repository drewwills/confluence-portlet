package edu.illinois.my.wiki.portlet.action;

import javax.portlet.ActionResponse;

public interface ActionFactory {
    PortletAction<?> create(String actionRequestParameter,
            ActionResponse portletActionResponse);

    PortletAction<?> createFirstVisitAction();
}
