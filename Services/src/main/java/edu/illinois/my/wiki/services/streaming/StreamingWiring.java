package edu.illinois.my.wiki.services.streaming;

import com.google.inject.AbstractModule;


public class StreamingWiring extends AbstractModule {
    public StreamingWiring() {}

    @Override
    protected void configure() {
        bind(ObjectStreamer.class).to(ObjectStreamObjectStreamer.class);
    }
}
