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
