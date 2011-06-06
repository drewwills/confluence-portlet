package edu.illinois.my.wiki.portlet.action;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.ViewParameters;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.action.result.ListingResultFactory;
import edu.illinois.my.wiki.portlet.error.ErrorKeys;
import edu.illinois.my.wiki.portlet.http.Communicator;
import edu.illinois.my.wiki.services.Search;
import edu.illinois.my.wiki.services.WikiLocation;
import edu.illinois.my.wiki.services.WikiLocationsService;
import edu.illinois.my.wiki.services.parameters.Parameters;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;
import edu.illinois.my.wiki.services.parameters.ParametersBuilderImpl;

final class SearchAction implements WikiLocationsAction<Search> {
    private final Communicator communicator;
    private final ActionResultFactory resultFactory;

    private static final String JSP = "/search.jsp";
    private static final ImmutableList<WikiLocation> EMPTY = ImmutableList.of();
    private static final String ONLY_WHITESPACE_REGEX = "^\\s+$";
    private static final String STAR = "*";

    @Inject
    SearchAction(Communicator communicator, ActionResultFactory resultFactory) {
        this.communicator = communicator;
        this.resultFactory = resultFactory;
    }

    @Override
    public ActionResult execute(Parameters renderParameters, Username username) {
        if (renderParameters.contains(ViewParameters.QUERY)) {
            return getQueryResults(renderParameters, username);
        } else {
            return getSearchPage();
        }
    }

    private ActionResult getQueryResults(Parameters renderParameters,
            Username username) {
        String query = renderParameters.get(ViewParameters.QUERY);
        if (isBlank(query)) {
            return resultFactory.createErrorMessage(ErrorKeys.BLANK_QUERY);
        }
        if (query.startsWith(STAR)) {
            return resultFactory.createErrorMessage(ErrorKeys.STAR_QUERY);
        }
        return sendQuery(query, username);
    }

    private ActionResult sendQuery(String query, Username username) {
        ListingResultFactory listingBuilder =
                resultFactory.createSearchFactory(query, JSP);
        WikiLocationsService service = new Search();
        ParametersBuilder wikiParameters = new ParametersBuilderImpl();
        wikiParameters.add(Search.QUERY, query);
        return communicator.requestListing(username, listingBuilder, service,
                wikiParameters);
    }

    private ActionResult getSearchPage() {
        ListingResultFactory listingBuilder =
                resultFactory.createListingFactory(JSP);
        return listingBuilder.createListing(EMPTY);
    }

    private boolean isBlank(String query) {
        return query.isEmpty() || query.matches(ONLY_WHITESPACE_REGEX);
    }
}
