package edu.illinois.my.wiki.plugin.servlet;

import com.google.inject.Singleton;

import edu.illinois.my.wiki.plugin.servlet.action.RecentlyUpdatedAction;

@Singleton
public final class RecentlyUpdatedServlet extends
        PortalServlet<RecentlyUpdatedAction> {
    private static final long serialVersionUID = 1L;
}
