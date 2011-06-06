package edu.illinois.my.wiki.plugin.servlet.execution.services.impl;

import com.atlassian.confluence.user.UserAccessor;
import com.atlassian.user.User;
import com.google.inject.Inject;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.execution.services.UserLookup;
import edu.illinois.my.wiki.services.WikiService;

final class UserAccessorWrapper implements UserLookup {
    private final UserAccessor userAccessor;
    private final ActionResponseFactory responseFactory;

    private static final String NO_USER_OUTGOING =
            "This user does not appear to have an account.";
    private static final String NO_USER_LOG = "User does not have an account.";

    @Inject
    UserAccessorWrapper(UserAccessor userAccessor,
            ActionResponseFactory responseFactory) {
        this.userAccessor = userAccessor;
        this.responseFactory = responseFactory;
    }

    @Override
    public User getUser(Username username) {
        User user = userAccessor.getUser(username.toString());
        if (user != null) {
            return user;
        } else {
            throw responseFactory.createException(NO_USER_LOG,
                    WikiService.NO_USER_ERROR, NO_USER_OUTGOING);
        }
    }
}
