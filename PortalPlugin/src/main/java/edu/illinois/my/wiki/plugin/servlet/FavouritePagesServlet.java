package edu.illinois.my.wiki.plugin.servlet;

import com.google.inject.Singleton;

import edu.illinois.my.wiki.plugin.servlet.action.FavouritePagesAction;

@Singleton
public final class FavouritePagesServlet extends
        PortalServlet<FavouritePagesAction> {
    private static final long serialVersionUID = 1L;
}
