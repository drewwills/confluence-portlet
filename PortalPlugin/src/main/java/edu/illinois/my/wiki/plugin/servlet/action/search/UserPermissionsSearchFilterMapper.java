package edu.illinois.my.wiki.plugin.servlet.action.search;

import org.apache.lucene.misc.ChainedFilter;
import org.apache.lucene.search.Filter;

import com.atlassian.confluence.search.lucene.filter.ContentPermissionsFilter;
import com.atlassian.confluence.search.lucene.filter.SpacePermissionsFilterDao;
import com.atlassian.confluence.search.v2.lucene.LuceneSearchFilterMapper;
import com.atlassian.confluence.user.UserAccessor;
import com.atlassian.user.User;
import com.atlassian.user.search.page.Pager;

/**
 * Creates the Lucene search filter based on user's access.
 * <p>
 * In the Confluence system, mappers sit between the <code>SearchManager</code>
 * which takes Confluence-level search objects like <code>SearchFilter</code>
 * and the Lucene search subsystem like
 * <code>org.apache.lucene.search.Filter</code>. A mapper converts the
 * Confluence search objects into their Lucene equivalent. This mapper will be
 * used whenever the {@link UserPermissionsSearchFilter} is specified as the
 * <code>SearchFilter</code> in a search. This mapper is registered as a
 * Confluence plugin which is used whenever
 * <code>UserPermissionsSearchFilter</code> is used. Since this class is
 * registered, Spring setter injection can be used to get the needed search
 * subsystem objects.
 * <p>
 * See
 * <code>com.atlassian.confluence.search.v2.lucene.mapper.ContentPermissionsLuceneSearchFilterMapper</code>
 * and
 * <code>com.atlassian.confluence.search.v2.lucene.mapper.SiteSearchPermissionsSearchFilterMapper</code>
 */
public class UserPermissionsSearchFilterMapper implements
        LuceneSearchFilterMapper<UserPermissionsSearchFilter> {
    private UserAccessor userAccessor;
    private SpacePermissionsFilterDao spacePermissionsFilterDao;
    private Filter inSpaceFilter;

    @Override
    public Filter convertToLuceneSearchFilter(UserPermissionsSearchFilter filter) {
        User user = filter.getUser();
        // Confluence does not often use generics
        @SuppressWarnings("rawtypes")
		Pager groups = userAccessor.getGroups(user);
        if (!userAccessor.isSuperUser(user)) {
            Filter[] filters =
                    {
                            new UserSpacePermissionsFilter(
                                    spacePermissionsFilterDao, inSpaceFilter,
                                    user),
                            new ContentPermissionsFilter(user, groups) };
            return new ChainedFilter(filters, ChainedFilter.AND);
        } else {
            return new ContentPermissionsFilter(user, groups);
        }
    }

    public void setUserAccessor(UserAccessor userAccessor) {
        this.userAccessor = userAccessor;
    }

    public void setSpacePermissionsFilterDao(
            SpacePermissionsFilterDao spacePermissionsFilterDao) {
        this.spacePermissionsFilterDao = spacePermissionsFilterDao;
    }

    public void setInSpaceFilter(Filter inSpaceFilter) {
        this.inSpaceFilter = inSpaceFilter;
    }
}
