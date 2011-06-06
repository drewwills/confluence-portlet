package edu.illinois.my.wiki.portlet.http;

import edu.illinois.my.helpers.ExposingBindModule;
import edu.illinois.my.wiki.portlet.http.handler.HandlerWiring;
import edu.illinois.my.wiki.portlet.http.services.ServicesWiring;
import edu.illinois.my.wiki.portlet.http.services.WikiServer;

public final class HttpWiring extends ExposingBindModule {
    private final WikiServer wikiServer;

    public HttpWiring(WikiServer wikiServer) {
        this.wikiServer = wikiServer;
    }

    @Override
    protected void configure() {
        install(new ServicesWiring(wikiServer));

        install(new HandlerWiring());

        exposingBind(SecureUriGenerator.class, RegisteringUriGenerator.class);
        exposingBind(Communicator.class, HttpClientCommunicator.class);
    }
}
