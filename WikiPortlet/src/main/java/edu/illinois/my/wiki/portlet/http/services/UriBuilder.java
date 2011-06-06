package edu.illinois.my.wiki.portlet.http.services;

import java.net.URI;

import edu.illinois.my.wiki.services.parameters.Parameters;

public interface UriBuilder {
    URI createUri(String urlPath, Parameters wikiParameters);
}
