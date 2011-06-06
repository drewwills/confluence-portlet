package edu.illinois.my.wiki.portlet.action;

import edu.illinois.my.helpers.ExposingBindModule;
import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.action.result.ResultWiring;
import edu.illinois.my.wiki.portlet.error.ErrorMessages;

public final class PortletActionWiring extends ExposingBindModule {
    private final ErrorMessages errorMessages;

    public PortletActionWiring(ErrorMessages errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    protected void configure() {
        install(new ResultWiring(errorMessages));
        expose(ActionResultFactory.class);

        bind(SearchAction.class);
        bind(SimpleListingActionFactory.class).to(
                SimpleListingActionFactoryImpl.class);
        exposingBind(ActionFactory.class, ActionFactoryImpl.class);
    }
}
