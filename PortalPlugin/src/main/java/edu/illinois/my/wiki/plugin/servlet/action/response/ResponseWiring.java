package edu.illinois.my.wiki.plugin.servlet.action.response;

import edu.illinois.my.helpers.ExposingBindModule;
import edu.illinois.my.wiki.services.streaming.StreamingWiring;

public final class ResponseWiring extends ExposingBindModule {
    public ResponseWiring() {}

    @Override
    protected void configure() {
        install(new StreamingWiring());
        exposingBind(ActionResponseFactory.class,
                ActionResponseFactoryImpl.class);
    }
}
