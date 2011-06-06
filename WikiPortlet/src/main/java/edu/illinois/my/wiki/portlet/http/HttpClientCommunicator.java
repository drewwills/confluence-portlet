package edu.illinois.my.wiki.portlet.http;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;

import com.google.inject.Inject;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ListingResultFactory;
import edu.illinois.my.wiki.portlet.http.handler.WikiResponseReceiverFactory;
import edu.illinois.my.wiki.services.WikiLocationsService;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;

/**
 * Uses HttpClient to perform GET communication.
 */
final class HttpClientCommunicator implements Communicator {
    private final SecureUriGenerator uriGenerator;
    private final HttpClient client;
    private final WikiResponseReceiverFactory receiverFactory;

    @Inject
    HttpClientCommunicator(SecureUriGenerator uriGenerator, HttpClient client,
            WikiResponseReceiverFactory receiverFactory) {
        this.uriGenerator = uriGenerator;
        this.client = client;
        this.receiverFactory = receiverFactory;
    }

    @Override
    public ActionResult requestListing(Username username,
            ListingResultFactory listingResultFactory,
            WikiLocationsService service, ParametersBuilder parameters) {
        URI serviceUri = uriGenerator.generate(username, service, parameters);
        HttpGet httpget = new HttpGet(serviceUri);
        ResponseHandler<ActionResult> handler =
                receiverFactory.create(username, listingResultFactory);
        return throwingExecute(httpget, handler);
    }

    private ActionResult throwingExecute(HttpGet httpget,
            ResponseHandler<ActionResult> handler) {
        try {
            return client.execute(httpget, handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
