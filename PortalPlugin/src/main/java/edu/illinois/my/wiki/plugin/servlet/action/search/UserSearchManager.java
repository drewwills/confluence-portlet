package edu.illinois.my.wiki.plugin.servlet.action.search;

import com.atlassian.confluence.search.v2.SearchQuery;
import com.atlassian.user.User;
import com.google.common.collect.ImmutableList;

import edu.illinois.my.wiki.services.WikiLocation;

public interface UserSearchManager {
    enum Sort {
        MODIFICATION_DATE, NONE
    };

    ImmutableList<WikiLocation> search(SearchQuery query, User user);

    ImmutableList<WikiLocation> search(SearchQuery query, User user,
            int maxResults, Sort sortType);
}
