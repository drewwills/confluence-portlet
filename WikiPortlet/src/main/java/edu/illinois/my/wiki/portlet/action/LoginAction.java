package edu.illinois.my.wiki.portlet.action;

import java.net.URI;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.ViewParameters;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.RedirectResultFactory;
import edu.illinois.my.wiki.portlet.http.SecureUriGenerator;
import edu.illinois.my.wiki.services.Login;
import edu.illinois.my.wiki.services.parameters.Parameters;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;
import edu.illinois.my.wiki.services.parameters.ParametersBuilderImpl;

final class LoginAction implements PortletAction<Login> {
    private final SecureUriGenerator uriGenerator;
    private final RedirectResultFactory redirectResultFactory;

    LoginAction(SecureUriGenerator uriGenerator,
            RedirectResultFactory redirectResultFactory) {
        this.uriGenerator = uriGenerator;
        this.redirectResultFactory = redirectResultFactory;
    }

    @Override
    public ActionResult execute(Parameters renderParameters, Username username) {
        ParametersBuilder wikiParameters = new ParametersBuilderImpl();
        setLocationParameter(renderParameters, wikiParameters);
        URI redirectUri = createRedirectUrl(username, wikiParameters);
        return redirectResultFactory.create(redirectUri);
    }

    private void setLocationParameter(Parameters renderParameters,
            ParametersBuilder wikiParameters) {
        String location = renderParameters.get(ViewParameters.LOCATION);
        if (!location.isEmpty()) {
            wikiParameters.add(Login.LOCATION, location);
        }
    }

    private URI createRedirectUrl(Username username,
            ParametersBuilder parameters) {
        Login wikiService = new Login();
        return uriGenerator.generate(username, wikiService, parameters);
    }
}
