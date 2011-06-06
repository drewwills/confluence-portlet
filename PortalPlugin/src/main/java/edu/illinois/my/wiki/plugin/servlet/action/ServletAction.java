package edu.illinois.my.wiki.plugin.servlet.action;

import com.atlassian.user.User;

import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionException;
import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionResponse;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.Parameters;

/**
 * Implements the specified <code>WikiService</code>.
 * 
 * @param <S> the service that will be performed
 * @param <T> the possible data result of that service
 */
public interface ServletAction<W extends WikiService<?>> {
    /**
     * Performs the service.
     * 
     * @param user for whom the action is performed
     * @param requestParameters possible settings for the action
     * @return response to be transmitted
     * @throws ServletActionException when a known error happens and an error
     *         code should be sent
     * @throws RuntimeException when an unrecoverable error happens
     */
    ServletActionResponse createResponse(User user, Parameters requestParameters)
            throws ServletActionException;
}
