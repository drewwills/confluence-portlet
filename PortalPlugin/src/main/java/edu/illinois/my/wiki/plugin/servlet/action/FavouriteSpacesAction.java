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

import java.util.List;

import com.atlassian.confluence.labels.LabelManager;
import com.atlassian.confluence.spaces.Space;
import com.atlassian.user.User;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.services.FavouriteSpaces;
import edu.illinois.my.wiki.services.WikiLocation;
import edu.illinois.my.wiki.services.WikiLocationImpl;
import edu.illinois.my.wiki.services.parameters.Parameters;

/**
 * Requests a user's favourite spaces.
 * 
 * Currently, this is the simplest action and uses a single manager. If only all
 * the actions could have been this simple.
 */
public final class FavouriteSpacesAction extends
        WikiLocationListAction<FavouriteSpaces> {
    private final LabelManager labelManager;

    @Inject
    FavouriteSpacesAction(LabelManager labelManager,
            ActionResponseFactory responseFactory) {
        super(responseFactory);
        this.labelManager = labelManager;
    }

    @Override
    protected ImmutableList<WikiLocation> createLocationsResponse(User user,
            Parameters requestParameters) {
        // Confluece does not often use generics
        @SuppressWarnings("unchecked")
        List<Space> favouriteSpaces =
                labelManager.getFavouriteSpaces(user.getName());
        ImmutableList.Builder<WikiLocation> spacesBuilder =
                ImmutableList.builder();
        for (Space space : favouriteSpaces) {
            spacesBuilder.add(fromSpace(space));
        }
        return spacesBuilder.build();
    }

    private WikiLocation fromSpace(Space space) {
        return new WikiLocationImpl(space.getName(), space.getUrlPath(),
                space.getLastModificationDate().getTime(),
                space.getLastModifierName());
    }
}
