package edu.illinois.my.wiki.portlet.http;

import java.net.URI;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;

public interface SecureUriGenerator {
    URI generate(Username username, WikiService<?> service,
            ParametersBuilder parameters);
}
