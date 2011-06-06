package edu.illinois.my.wiki.portlet;

import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;

import edu.illinois.my.security.CallbackModule;
import edu.illinois.my.wiki.portlet.action.PortletActionWiring;
import edu.illinois.my.wiki.portlet.error.ErrorMessages;
import edu.illinois.my.wiki.portlet.error.ImmutableMapErrorMessages;
import edu.illinois.my.wiki.portlet.http.HttpWiring;
import edu.illinois.my.wiki.portlet.http.services.WikiServer;
import edu.illinois.my.wiki.portlet.http.services.WikiServerImpl;

final class PortletWiring extends AbstractModule {
    private final ImmutableMap<String, String> serverUrls;
    private final ImmutableMap<String, String> errorMessages;

    PortletWiring(ImmutableMap<String, String> serverUrls,
            ImmutableMap<String, String> errorMessages) {
        this.serverUrls = serverUrls;
        this.errorMessages = errorMessages;
    }

    @Override
    protected void configure() {
        install(new CallbackModule(serverUrls));
        install(new PortletActionWiring(errorMessages()));
        install(new HttpWiring(wikiServer()));

        bind(PortletActionExecutor.class).to(ErrorHandlingExecutor.class);
        bind(ActionResultSessionFactory.class).to(WrappedSessionFactory.class);
    }

    private WikiServer wikiServer() {
        String wikiServer = serverUrls.get(ServerKeys.WIKI);
        if (wikiServer != null) {
            return new WikiServerImpl(wikiServer);
        } else {
            throw new RuntimeException(
                    "Missing Wiki server url from configuration file.");
        }
    }

    private ErrorMessages errorMessages() {
        return new ImmutableMapErrorMessages(errorMessages);
    }
}
