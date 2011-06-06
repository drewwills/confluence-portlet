package edu.illinois.my.wiki.portlet.http.handler;

import edu.illinois.my.helpers.ExposingBindModule;
import edu.illinois.my.wiki.services.streaming.StreamingWiring;

public final class HandlerWiring extends ExposingBindModule {
    public HandlerWiring() {}

    @Override
    protected void configure() {
        install(new StreamingWiring());

        exposingBind(WikiResponseReceiverFactory.class,
                WikiResponseReceiverFactoryImpl.class);
    }
}
