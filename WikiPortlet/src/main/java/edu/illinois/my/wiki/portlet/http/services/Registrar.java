package edu.illinois.my.wiki.portlet.http.services;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;

public interface Registrar {
    void addCredentials(Username username, ParametersBuilder parametersBuilder);
}
