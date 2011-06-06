package edu.illinois.my.wiki.portlet.http.handler;

import org.apache.http.client.ResponseHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ListingResultFactory;
import edu.illinois.my.wiki.services.streaming.ObjectStreamer;

final class WikiResponseReceiverFactoryImpl implements
        WikiResponseReceiverFactory {
    private final Provider<ObjectStreamer> streamerProvider;

    @Inject
    WikiResponseReceiverFactoryImpl(Provider<ObjectStreamer> streamerProvider) {
        this.streamerProvider = streamerProvider;
    }

    @Override
    public ResponseHandler<ActionResult> create(Username username,
            ListingResultFactory listingResultFactory) {
        return new WikiResponseReceiver(username, listingResultFactory,
                streamerProvider.get());
    }
}
