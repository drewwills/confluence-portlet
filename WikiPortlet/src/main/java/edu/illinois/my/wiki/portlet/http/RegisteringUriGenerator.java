package edu.illinois.my.wiki.portlet.http;

import java.net.URI;

import com.google.inject.Inject;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.http.services.Registrar;
import edu.illinois.my.wiki.portlet.http.services.UriBuilder;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.Parameters;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;

final class RegisteringUriGenerator implements SecureUriGenerator {
    private final Registrar registrar;
    private final UriBuilder uriBuilder;

    private static final String SEPARATOR = "/";

    @Inject
    RegisteringUriGenerator(Registrar registrar, UriBuilder uriBuilder) {
        this.registrar = registrar;
        this.uriBuilder = uriBuilder;
    }

    @Override
    public URI generate(Username username, WikiService<?> service,
            ParametersBuilder parameters) {
        registrar.addCredentials(username, parameters);
        Parameters wikiParameters = parameters.build();
        String urlPath = buildUrlPath(service);
        return uriBuilder.createUri(urlPath, wikiParameters);
    }

    private String buildUrlPath(WikiService<?> service) {
        String serviceUrl = service.getUrl();
        String urlPath = buildUrlPath(serviceUrl);
        return urlPath;
    }

    private String buildUrlPath(String serviceUrl) {
        StringBuilder urlPathBuilder = new StringBuilder();
        urlPathBuilder.append(WikiService.CONTEXT_PATH);
        urlPathBuilder.append(WikiService.PORTAL_SERVLETS_PATH);
        urlPathBuilder.append(serviceUrl);
        urlPathBuilder.append(SEPARATOR);
        return urlPathBuilder.toString();
    }
}
