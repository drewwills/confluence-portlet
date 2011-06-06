package edu.illinois.my.wiki.plugin.servlet.action;

import com.google.inject.PrivateModule;

import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.action.response.ResponseWiring;
import edu.illinois.my.wiki.plugin.servlet.action.search.SearchWiring;

public final class ActionWiring extends PrivateModule {
    public ActionWiring() {}

    @Override
    protected void configure() {
        install(new ResponseWiring());
        expose(ActionResponseFactory.class);
        install(new SearchWiring());

        exposingBind(FavouritePagesAction.class);
        exposingBind(FavouriteSpacesAction.class);
        exposingBind(LoginAction.class);
        exposingBind(RecentlyUpdatedAction.class);
        exposingBind(SearchAction.class);
    }

    private void exposingBind(Class<?> binding) {
        bind(binding);
        expose(binding);
    }
}
