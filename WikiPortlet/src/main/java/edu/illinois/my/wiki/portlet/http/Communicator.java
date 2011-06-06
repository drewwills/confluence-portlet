package edu.illinois.my.wiki.portlet.http;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ListingResultFactory;
import edu.illinois.my.wiki.services.WikiLocationsService;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;

public interface Communicator {
    ActionResult requestListing(Username username,
            ListingResultFactory listingResultFactory,
            WikiLocationsService service, ParametersBuilder parameters);
}
