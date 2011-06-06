package edu.illinois.my.wiki.portlet.http.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import edu.illinois.my.wiki.services.parameters.Parameters;

final class UriUtilsWrapper implements UriBuilder {
    private final WikiServer server;

    private static final String SECURE_HTTP = "https";
    private static final String ENCODING = "UTF-8";
    private static final int defaultPort = -1;
    private static final String fragment = null;

    @Inject
    UriUtilsWrapper(WikiServer server) {
        this.server = server;
    }

    @Override
    public URI createUri(String urlPath, Parameters wikiParameters) {
        String serverAddress = server.asString();
        ImmutableList<NameValuePair> parameters =
                wikiParameters.asNameValuePairs();
        String path = URLEncodedUtils.format(parameters, ENCODING);
        try {
            return URIUtils.createURI(SECURE_HTTP, serverAddress, defaultPort,
                    urlPath, path, fragment);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
