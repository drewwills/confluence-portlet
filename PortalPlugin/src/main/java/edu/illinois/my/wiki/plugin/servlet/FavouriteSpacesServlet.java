package edu.illinois.my.wiki.plugin.servlet;

import com.google.inject.Singleton;

import edu.illinois.my.wiki.plugin.servlet.action.FavouriteSpacesAction;

@Singleton
public final class FavouriteSpacesServlet extends
        PortalServlet<FavouriteSpacesAction> {
    private static final long serialVersionUID = 1L;
}
