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
package edu.illinois.my.wiki.plugin.servlet.execution;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.atlassian.user.User;
import com.google.inject.Inject;

import edu.illinois.my.names.UsernameImpl;
import edu.illinois.my.names.Username;
import edu.illinois.my.security.keys.RequestKey;
import edu.illinois.my.security.keys.RequestKeyImpl;
import edu.illinois.my.wiki.plugin.servlet.action.ServletAction;
import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionException;
import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionResponse;
import edu.illinois.my.wiki.plugin.servlet.execution.services.ExceptionLogger;
import edu.illinois.my.wiki.plugin.servlet.execution.services.Validator;
import edu.illinois.my.wiki.plugin.servlet.execution.services.UserLookup;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.Parameters;

final class ValidatingExecutor implements ServletActionExecutor {
    private final Validator requestValidator;
    private final UserLookup userLookup;
    private final ExceptionLogger exceptionLogger;
    private final ActionResponseFactory responseFactory;

    @Inject
    ValidatingExecutor(Validator validator, UserLookup userLookup,
            ExceptionLogger exceptionLogger,
            ActionResponseFactory responseFactory) {
        this.requestValidator = validator;
        this.userLookup = userLookup;
        this.exceptionLogger = exceptionLogger;
        this.responseFactory = responseFactory;
    }

    @Override
    public void manageExecution(Parameters requestParameters, String ip,
            ServletAction<?> action, HttpServletResponse httpResponse)
            throws IOException {
        ServletActionResponse actionResponse =
                manageExecution(requestParameters, ip, action);
        actionResponse.send(httpResponse);
    }

    private ServletActionResponse manageExecution(Parameters requestParameters,
            String ip, ServletAction<?> action) {
        try {
            return throwingManageExecution(requestParameters, ip, action);
        } catch (ServletActionException exception) {
            exceptionLogger.logServletAction(requestParameters, ip, exception);
            return exception;
        } catch (RuntimeException exception) {
            exceptionLogger.logRuntime(requestParameters, ip, exception);
            return responseFactory.createSendException(exception);
        }
    }

    private ServletActionResponse throwingManageExecution(
            Parameters requestParameters, String ip, ServletAction<?> action) {
        Username username = getUsername(requestParameters);
        RequestKey key = getCallbackKey(requestParameters);
        requestValidator.validate(username, key, ip);
        User user = userLookup.getUser(username);
        return action.createResponse(user, requestParameters);
    }

    private Username getUsername(Parameters requestParameters) {
        String username = requestParameters.get(WikiService.USERNAME);
        return new UsernameImpl(username);
    }

    private RequestKey getCallbackKey(Parameters requestParameters) {
        String callbackKey = requestParameters.get(WikiService.KEY);
        return new RequestKeyImpl(callbackKey);
    }
}
