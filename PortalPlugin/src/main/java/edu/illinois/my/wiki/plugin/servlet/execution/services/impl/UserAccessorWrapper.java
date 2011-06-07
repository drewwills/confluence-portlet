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
