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

import com.atlassian.confluence.event.events.search.SearchPerformedEvent;
import com.atlassian.confluence.search.v2.SearchQuery;
import com.atlassian.confluence.search.v2.query.MultiTextFieldQuery;
import com.atlassian.event.EventManager;
import com.atlassian.user.User;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.action.search.UserSearchManager;
import edu.illinois.my.wiki.plugin.servlet.action.search.UserSearchManager.Sort;
import edu.illinois.my.wiki.services.Search;
import edu.illinois.my.wiki.services.WikiLocation;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.Parameters;

/**
 * Performs a text search on a provided term.
 * 
 * See
 * <code>com.atlassian.confluence.search.actions.SearchSiteAction.execute()</code>
 * . This is the search that is normally used by Confluence and what users
 * should expect to be replicated. The execute method calls
 * <code>buildSiteSearch</code> from
 * <code>com.atlassian.confluence.search.service.DefaultPredefinedSearchBuilder</code>
 * which has further details of how the search is performed. For
 * <code>Page</code>s' <code>Field</code> names see Page.lucene.xml in
 * confluence-project/confluence/src/etc/java/com/atlassian/confluence/pages See
 * also BaseDocumentBuilder.FieldName.CONTENT_BODY from
 * com.atlassian.bonnie.search.BaseDocumentBuilder for even more searching
 * references.
 * 
 * @see Search
 */
public final class SearchAction extends WikiLocationListAction<Search> {
    private final UserSearchManager searchManager;
    private final EventManager eventManager;
    private final ActionResponseFactory responseFactory;

    private static final int MAX_RESULTS = 20;
    private static final String MISSING_QUERY =
            "The query was not included in the search request.";

    @Inject
    SearchAction(UserSearchManager searchManager, EventManager eventManager,
            ActionResponseFactory responseFactory) {
        super(responseFactory);
        this.searchManager = searchManager;
        this.eventManager = eventManager;
        this.responseFactory = responseFactory;
    }

    @Override
    protected ImmutableList<WikiLocation> createLocationsResponse(User user,
            Parameters requestParameters) {
        String query = requestParameters.get(Search.QUERY);
        if (query.isEmpty()) {
            throw responseFactory.createException(MISSING_QUERY,
                    WikiService.BAD_REQUEST, MISSING_QUERY);
        }
        // This MultiTextFieldQuery is an abomination, but it is taken from
        // siteSearchQuery, which means this is how the Confluence text search
        // works as well.
        SearchQuery textQuery =
                new MultiTextFieldQuery(query, "title", "labelText",
                        "contentBody", "filename", "username", "fullName",
                        "email", "from", "recipients");
        ImmutableList<WikiLocation> results =
                searchManager.search(textQuery, user, MAX_RESULTS, Sort.NONE);
        eventManager.publishEvent(new SearchPerformedEvent(this, textQuery,
                user, results.size()));
        return results;
    }
}
