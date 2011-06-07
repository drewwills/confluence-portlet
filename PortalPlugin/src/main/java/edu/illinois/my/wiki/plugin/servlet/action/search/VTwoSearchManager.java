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
package edu.illinois.my.wiki.plugin.servlet.action.search;

import com.atlassian.confluence.search.v2.ContentSearch;
import com.atlassian.confluence.search.v2.ISearch;
import com.atlassian.confluence.search.v2.InvalidSearchException;
import com.atlassian.confluence.search.v2.ResultFilter;
import com.atlassian.confluence.search.v2.SearchFilter;
import com.atlassian.confluence.search.v2.SearchManager;
import com.atlassian.confluence.search.v2.SearchQuery;
import com.atlassian.confluence.search.v2.SearchResult;
import com.atlassian.confluence.search.v2.SearchResults;
import com.atlassian.confluence.search.v2.filter.SubsetResultFilter;
import com.atlassian.confluence.search.v2.sort.ModifiedSort;
import com.atlassian.user.User;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import edu.illinois.my.wiki.services.WikiLocation;
import edu.illinois.my.wiki.services.WikiLocationImpl;
import edu.umd.cs.findbugs.annotations.Nullable;

final class VTwoSearchManager implements UserSearchManager {
    private final SearchManager searchManager;

    @Inject
    VTwoSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
    }

    @Override
    public ImmutableList<WikiLocation> search(SearchQuery query, User user) {
        return contentSearch(query, null, user, null);
    }

    @Override
    public ImmutableList<WikiLocation> search(SearchQuery query, User user,
            int maxResults, Sort sortType) {
        ModifiedSort modifiedSort = null;
        if (sortType.equals(Sort.MODIFICATION_DATE))
            modifiedSort = ModifiedSort.DESCENDING;
        ResultFilter resultSizeFilter = new SubsetResultFilter(maxResults);
        return contentSearch(query, modifiedSort, user, resultSizeFilter);
    }

    private ImmutableList<WikiLocation> contentSearch(SearchQuery query,
            @Nullable ModifiedSort modifiedSort, User user,
            @Nullable ResultFilter resultFilter) {
        SearchFilter searchFilter = new UserPermissionsSearchFilter(user);
        ISearch searchParameters =
                new ContentSearch(query, modifiedSort, searchFilter,
                        resultFilter);
        return search(searchParameters);
    }

    private ImmutableList<WikiLocation> search(ISearch parameters) {
        SearchResults results = throwingSearch(parameters);
        return convertResults(results);
    }

    private SearchResults throwingSearch(ISearch parameters) {
        try {
            return searchManager.search(parameters);
        } catch (InvalidSearchException e) {
            throw new RuntimeException(e);
        }
    }

    private ImmutableList<WikiLocation> convertResults(SearchResults results) {
        ImmutableList.Builder<WikiLocation> wikiPages = ImmutableList.builder();
        for (SearchResult result : results) {
            wikiPages.add(fromResult(result));
        }
        return wikiPages.build();
    }

    private WikiLocation fromResult(SearchResult result) {
        return new WikiLocationImpl(result.getDisplayTitle(),
                result.getUrlPath(),
                result.getLastModificationDate().getTime(),
                result.getLastModifier());
    }
}
