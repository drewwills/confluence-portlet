package edu.illinois.my.wiki.plugin.servlet;

import com.google.inject.servlet.ServletModule;

import edu.illinois.my.wiki.plugin.servlet.action.ActionWiring;
import edu.illinois.my.wiki.plugin.servlet.action.ServletAction;
import edu.illinois.my.wiki.plugin.servlet.execution.ExecutionWiring;
import edu.illinois.my.wiki.services.FavouritePages;
import edu.illinois.my.wiki.services.FavouriteSpaces;
import edu.illinois.my.wiki.services.Login;
import edu.illinois.my.wiki.services.RecentlyUpdated;
import edu.illinois.my.wiki.services.Search;
import edu.illinois.my.wiki.services.WikiService;

/**
 * Configures the servlet mappings like a web.xml.
 * 
 * @author James Kionka
 */
public final class ServletWiring extends ServletModule {
    private static final String ANY_ENDING_PATTERN = "/*";

    public ServletWiring() {}

    @Override
    public void configureServlets() {
        install(new ActionWiring());
        install(new ExecutionWiring());

        registerServlet(new Login(), LoginServlet.class);
        registerServlet(new RecentlyUpdated(), RecentlyUpdatedServlet.class);
        registerServlet(new FavouriteSpaces(), FavouriteSpacesServlet.class);
        registerServlet(new FavouritePages(), FavouritePagesServlet.class);
        registerServlet(new Search(), SearchServlet.class);
    }

    private <S extends WikiService<?>> void registerServlet(
            S service,
            Class<? extends PortalServlet<? extends ServletAction<S>>> servletClass) {
        String serviceUrl = service.getUrl();
        String urlPath = addPathAndWildcard(serviceUrl);
        serve(urlPath).with(servletClass);
    }

    private String addPathAndWildcard(String path) {
        StringBuilder builder = new StringBuilder();
        builder.append(WikiService.PORTAL_SERVLETS_PATH);
        builder.append(path);
        builder.append(ANY_ENDING_PATTERN);
        return builder.toString();
    }
}
