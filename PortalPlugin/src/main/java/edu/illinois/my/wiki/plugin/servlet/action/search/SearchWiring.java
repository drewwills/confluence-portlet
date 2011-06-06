package edu.illinois.my.wiki.plugin.servlet.action.search;

import com.google.inject.AbstractModule;

public final class SearchWiring extends AbstractModule {
    public SearchWiring() {}

    @Override
    protected void configure() {
        bind(UserSearchManager.class).to(VTwoSearchManager.class);
    }
}
