package edu.illinois.my.wiki.plugin.servlet;

import com.google.inject.Singleton;

import edu.illinois.my.wiki.plugin.servlet.action.SearchAction;

@Singleton
public class SearchServlet extends PortalServlet<SearchAction> {
    private static final long serialVersionUID = 1L;
}
