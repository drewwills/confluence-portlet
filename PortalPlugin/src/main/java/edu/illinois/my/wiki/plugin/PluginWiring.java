package edu.illinois.my.wiki.plugin;

import com.atlassian.confluence.labels.LabelManager;
import com.atlassian.confluence.search.v2.SearchManager;
import com.atlassian.confluence.user.UserAccessor;
import com.atlassian.event.EventManager;
import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;

import edu.illinois.my.security.CallbackModule;
import edu.illinois.my.wiki.plugin.servlet.ServletWiring;

public final class PluginWiring extends AbstractModule {
    private final ImmutableMap<String, String> serverConfiguration;
    private final UserAccessor userAccessor;
    private final EventManager eventManager;
    private final LabelManager labelManager;
    private final SearchManager searchManager;

    public PluginWiring(UserAccessor userAccessor, EventManager eventManager,
            LabelManager labelManager, SearchManager searchManager,
            ImmutableMap<String, String> serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
        this.userAccessor = userAccessor;
        this.eventManager = eventManager;
        this.labelManager = labelManager;
        this.searchManager = searchManager;
    }

    @Override
    protected void configure() {
        install(new CallbackModule(serverConfiguration));
        // UserAccessor is for execution.services
        bind(UserAccessor.class).toInstance(userAccessor);
        // SearchManager is for action.search
        bind(SearchManager.class).toInstance(searchManager);
        // EventManager and LabelManager are for servlet.action
        bind(EventManager.class).toInstance(eventManager);
        bind(LabelManager.class).toInstance(labelManager);

        install(new ServletWiring());
    }
}
