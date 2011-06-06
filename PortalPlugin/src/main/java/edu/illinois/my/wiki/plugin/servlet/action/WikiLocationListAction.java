package edu.illinois.my.wiki.plugin.servlet.action;

import com.atlassian.user.User;
import com.google.common.collect.ImmutableList;

import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionResponse;
import edu.illinois.my.wiki.services.WikiLocation;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.Parameters;

abstract class WikiLocationListAction<W extends WikiService<ImmutableList<WikiLocation>>>
        implements ServletAction<W> {
    private final ActionResponseFactory responseFactory;

    WikiLocationListAction(ActionResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    @Override
    public final ServletActionResponse createResponse(User user,
            Parameters requestParameters) {
        ImmutableList<WikiLocation> locations =
                createLocationsResponse(user, requestParameters);
        return responseFactory.createSendObject(locations);
    }

    abstract protected ImmutableList<WikiLocation> createLocationsResponse(
            User user, Parameters requestParameters);
}
