package edu.illinois.my.wiki.portlet.http.handler;

import org.apache.http.client.ResponseHandler;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ListingResultFactory;

public interface WikiResponseReceiverFactory {
    ResponseHandler<ActionResult> create(Username username,
            ListingResultFactory listingResultFactory);
}
