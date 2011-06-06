package edu.illinois.my.wiki.portlet.action.result;

import javax.portlet.ActionResponse;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.error.ErrorMessageKey;

public interface ActionResultFactory {
    ListingResultFactory createSearchFactory(String searchTerm, String jsp);

    ListingResultFactory createListingFactory(String jsp);

    RedirectResultFactory createRedirectFactory(ActionResponse actionResponse);

    ActionResult createWikiError(Username username, int statusCode,
            String statusText);

    ActionResult createException(Username username, Exception exception);

    ActionResult createPortalException(String logMessage, Exception exception);

    ActionResult createLoggedError(ErrorMessageKey userErrorKey,
            String logMessage);

    ActionResult createErrorMessage(ErrorMessageKey userErrorKey);

    ActionResult createFirstVisit();
}
