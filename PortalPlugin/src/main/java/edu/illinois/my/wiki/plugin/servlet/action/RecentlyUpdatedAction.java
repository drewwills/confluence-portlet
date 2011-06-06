package edu.illinois.my.wiki.plugin.servlet.action;

import com.atlassian.confluence.search.v2.SearchQuery;
import com.atlassian.confluence.search.v2.query.AllQuery;
import com.atlassian.confluence.search.v2.query.BoostingQuery;
import com.atlassian.user.User;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.action.search.UserSearchManager;
import edu.illinois.my.wiki.plugin.servlet.action.search.UserSearchManager.Sort;
import edu.illinois.my.wiki.services.RecentlyUpdated;
import edu.illinois.my.wiki.services.WikiLocation;
import edu.illinois.my.wiki.services.parameters.Parameters;

/**
 * Searches for all recently updated content the given user could view.
 * 
 * This class produces very similar results in comparison to the Recently
 * Updated macro on the Dashboard. The macro is called
 * recently-updated-dashboard and surprisingly is not part of the source code.
 * However, it is downloadable from <a href="https://studio.plugins.atlassian.com/wiki/display/DASHMACROS/Confluence+Dashboard+Macros"
 * >Atlassian Plugins Studio</a>. It uses SmartList, which is the deprecated
 * search system. In all the managers that show recently updated content, the
 * code eventually comes to a DAO object which returns the data by using a
 * Hibernate query. This class uses the new search system with a simple query
 * with highly satisfactory results.
 * 
 * @see RecentlyUpdated
 */
public final class RecentlyUpdatedAction extends
        WikiLocationListAction<RecentlyUpdated> {
    private final static int maxResults = 20;
    private final UserSearchManager searchManager;

    @Inject
    RecentlyUpdatedAction(UserSearchManager searchManager,
            ActionResponseFactory responseFactory) {
        super(responseFactory);
        this.searchManager = searchManager;
    }

    @Override
    protected ImmutableList<WikiLocation> createLocationsResponse(User user,
            Parameters requestParameters) {
        SearchQuery searchQuery =
                new BoostingQuery(AllQuery.getInstance(), null);
        return searchManager.search(searchQuery, user, maxResults,
                Sort.MODIFICATION_DATE);
    }
}
