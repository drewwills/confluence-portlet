package edu.illinois.my.wiki.portlet.http.services;

import edu.illinois.my.helpers.ExposingBindModule;

public final class ServicesWiring extends ExposingBindModule {
    private final WikiServer wikiServer;

    public ServicesWiring(WikiServer wikiServer) {
        this.wikiServer = wikiServer;
    }

    @Override
    protected void configure() {
        bind(WikiServer.class).toInstance(wikiServer);
        exposingBind(Registrar.class, RequestRegistrarWrapper.class);
        exposingBind(UriBuilder.class, UriUtilsWrapper.class);
    }
}
