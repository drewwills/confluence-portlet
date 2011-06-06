package edu.illinois.my.wiki.portlet.action;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.action.result.ListingResultFactory;
import edu.illinois.my.wiki.portlet.http.Communicator;
import edu.illinois.my.wiki.services.WikiLocationsService;
import edu.illinois.my.wiki.services.parameters.Parameters;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;
import edu.illinois.my.wiki.services.parameters.ParametersBuilderImpl;

final class SimpleListingAction<W extends WikiLocationsService> implements
        WikiLocationsAction<W> {
    private final W service;
    private final String jsp;
    private final Communicator communicator;
    private final ActionResultFactory resultFactory;

    SimpleListingAction(W service, String jsp, Communicator communicator,
            ActionResultFactory resultFactory) {
        this.service = service;
        this.jsp = jsp;
        this.communicator = communicator;
        this.resultFactory = resultFactory;
    }

    @Override
    public ActionResult execute(Parameters renderParameters, Username username) {
        ListingResultFactory listingBuilder =
                resultFactory.createListingFactory(jsp);
        ParametersBuilder emptyWikiParameters = new ParametersBuilderImpl();
        return communicator.requestListing(username, listingBuilder, service,
                emptyWikiParameters);
    }
}
