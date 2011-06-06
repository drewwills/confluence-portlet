package edu.illinois.my.wiki.portlet.action;

import com.google.inject.Inject;
import com.google.inject.Provider;

import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.http.Communicator;
import edu.illinois.my.wiki.services.WikiLocationsService;

final class SimpleListingActionFactoryImpl implements
        SimpleListingActionFactory {
    private final Provider<Communicator> communicatorProvider;
    private final Provider<ActionResultFactory> resultFactoryProvider;

    @Inject
    SimpleListingActionFactoryImpl(Provider<Communicator> communicatorProvider,
            Provider<ActionResultFactory> resultFactoryProvider) {
        this.communicatorProvider = communicatorProvider;
        this.resultFactoryProvider = resultFactoryProvider;
    }

    @Override
    public <W extends WikiLocationsService> WikiLocationsAction<W> create(
            W service, String jsp) {
        Communicator communicator = communicatorProvider.get();
        ActionResultFactory resultFactory = resultFactoryProvider.get();
        return new SimpleListingAction<W>(service, jsp, communicator,
                resultFactory);
    }
}
