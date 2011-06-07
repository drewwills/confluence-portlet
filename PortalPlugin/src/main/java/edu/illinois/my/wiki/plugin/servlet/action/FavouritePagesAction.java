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

import com.atlassian.confluence.labels.Label;
import com.atlassian.confluence.labels.LabelManager;
import com.atlassian.confluence.labels.Namespace;
import com.atlassian.confluence.search.service.ContentTypeEnum;
import com.atlassian.confluence.search.v2.SearchQuery;
import com.atlassian.confluence.search.v2.query.BooleanQuery;
import com.atlassian.confluence.search.v2.query.ContentTypeQuery;
import com.atlassian.confluence.search.v2.query.LabelQuery;
import com.atlassian.user.User;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.action.search.UserSearchManager;
import edu.illinois.my.wiki.services.FavouritePages;
import edu.illinois.my.wiki.services.WikiLocation;
import edu.illinois.my.wiki.services.parameters.Parameters;

/**
 * Searches for a user's favourite pages.
 * 
 * Bizarrely, there is not an equivalent method to getFavouriteSpaces. This
 * requires us to perform a search for <code>Page</code>s that were given a
 * favourite label by the given user.
 */
public final class FavouritePagesAction extends
        WikiLocationListAction<FavouritePages> {
    private final UserSearchManager searchManager;
    private final LabelManager labelManager;

    private static final ImmutableList<WikiLocation> NO_LOCATIONS =
            ImmutableList.of();

    @Inject
    FavouritePagesAction(UserSearchManager searchManager,
            LabelManager labelManager, ActionResponseFactory responseFactory) {
        super(responseFactory);
        this.searchManager = searchManager;
        this.labelManager = labelManager;
    }

    @Override
    protected ImmutableList<WikiLocation> createLocationsResponse(User user,
            Parameters requestParameters) {
        Label userFavouritesData =
                new Label(LabelManager.FAVOURITE_LABEL, Namespace.PERSONAL,
                        user.getName());
        // LabelManager says this will look up the persistent version of it
        Label userFavourites = labelManager.getLabel(userFavouritesData);
        // this will happen when the user has not made a favourite page yet (?)
        if (userFavourites == null) {
            return NO_LOCATIONS;
        }
        SearchQuery favouritesQuery = new LabelQuery(userFavourites);
        SearchQuery pagesOnlyQuery = new ContentTypeQuery(ContentTypeEnum.PAGE);
        SearchQuery searchQuery =
                BooleanQuery.composeAndQuery(favouritesQuery, pagesOnlyQuery);
        return searchManager.search(searchQuery, user);
    }
}
