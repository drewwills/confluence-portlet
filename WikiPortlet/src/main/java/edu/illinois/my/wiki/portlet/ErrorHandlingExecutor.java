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
package edu.illinois.my.wiki.portlet;

import com.google.inject.Inject;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.PortletAction;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.error.ErrorKeys;
import edu.illinois.my.wiki.services.parameters.Parameters;

final class ErrorHandlingExecutor implements PortletActionExecutor {
    private final ActionResultFactory resultFactory;

    @Inject
    ErrorHandlingExecutor(ActionResultFactory resultFactory) {
        this.resultFactory = resultFactory;
    }

    @Override
    public ActionResult processAction(Parameters parameters, Username username,
            PortletAction<?> action) {
        if (username.isEmpty()) {
            String logMessage = "getRemoteUser() failed to return a user.";
            return resultFactory.createLoggedError(ErrorKeys.PORTAL, logMessage);
        }
        try {
            return action.execute(parameters, username);
        } catch (RuntimeException exception) {
            return resultFactory.createException(username, exception);
        }
    }
}
