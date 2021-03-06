/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.illinois.my.wiki.plugin.servlet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.atlassian.confluence.event.events.security.LoginEvent;
import com.atlassian.event.EventManager;
import com.atlassian.seraph.auth.DefaultAuthenticator;
import com.atlassian.user.User;
import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionResponse;
import edu.illinois.my.wiki.services.Login;
import edu.illinois.my.wiki.services.parameters.Parameters;

/**
 * Logs a user into Confluence and redirects them appropriately.
 * 
 * See <code>com.atlassian.confluence.user.ConfluenceAuthenicator</code> and
 * <code>com.atlassian.seraph.auth.DefaultAuthenticator</code>
 * 
 * @see Login
 */
@RequestScoped
public final class LoginAction implements ServletAction<Login> {
    private final HttpServletRequest request;
    private final HttpSession session;
    private final EventManager eventManager;
    private final ActionResponseFactory responseFactory;

    private static final String DEFAULT_LOCATION = "/";

    @Inject
    LoginAction(HttpServletRequest request, HttpSession session,
            EventManager eventManager, ActionResponseFactory responseFactory) {
        this.request = request;
        this.session = session;
        this.eventManager = eventManager;
        this.responseFactory = responseFactory;
    }

    @Override
    public ServletActionResponse createResponse(User user, Parameters requestParameters) {
        loginUser(user);
        publishLoginEvent(user.getName());
        String redirectUrlPath = getLocationParameter(requestParameters);
        return responseFactory.createRedirect(redirectUrlPath);
    }

    private void loginUser(User user) {
        session.setAttribute(DefaultAuthenticator.LOGGED_IN_KEY, user);
        session.setAttribute(DefaultAuthenticator.LOGGED_OUT_KEY, null);
    }

    private void publishLoginEvent(String username) {
        String sessionId = session.getId();
        String remoteIP = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        LoginEvent event =
                new LoginEvent(this, username, sessionId, remoteHost, remoteIP);
        eventManager.publishEvent(event);
    }

    private String getLocationParameter(Parameters requestParameters) {
        String redirectUrlPath = requestParameters.get(Login.LOCATION);
        if (!redirectUrlPath.isEmpty()) {
            return redirectUrlPath;
        } else {
            return DEFAULT_LOCATION;
        }
    }
}
