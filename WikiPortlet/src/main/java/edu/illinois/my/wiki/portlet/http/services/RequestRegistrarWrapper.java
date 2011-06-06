package edu.illinois.my.wiki.portlet.http.services;

import com.google.inject.Inject;

import edu.illinois.my.names.Username;
import edu.illinois.my.security.RequestRegistrar;
import edu.illinois.my.security.keys.RequestKey;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;

final class RequestRegistrarWrapper implements Registrar {
    private final RequestRegistrar registrar;

    @Inject
    RequestRegistrarWrapper(RequestRegistrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void addCredentials(Username username,
            ParametersBuilder parametersBuilder) {
        parametersBuilder.add(WikiService.USERNAME, username.asString());
        RequestKey key = registrar.registerRequest(username);
        parametersBuilder.add(WikiService.KEY, key.asString());
    }
}
