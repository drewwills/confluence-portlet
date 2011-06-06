package edu.illinois.my.wiki.portlet;

import javax.portlet.PortletSession;

interface ActionResultSessionFactory {
    ActionResultSession create(PortletSession session);
}
