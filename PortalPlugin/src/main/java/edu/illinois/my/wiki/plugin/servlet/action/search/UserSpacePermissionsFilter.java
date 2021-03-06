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

import java.io.IOException;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;

import com.atlassian.confluence.search.lucene.DocumentFieldName;
import com.atlassian.confluence.search.lucene.filter.MultiTermFilter;
import com.atlassian.confluence.search.lucene.filter.SpacePermissionsFilterDao;
import com.atlassian.user.User;

/**
 * Determines which spaces will be searched based on a user's access.
 * <p>
 * This class has mostly been lifted from
 * <code>com.atlassian.confluence.search.lucene.filter.SpacePermissionsFilter</code>
 * with a modification that allows the user to be specified by the search filter
 * instead of taken from session credentials.
 * <code>ContentPermissionsFilter</code> would also need to be recreated, but it
 * inconsistently allows User to be specified.
 */
// SpacePermissionsFilter does not declare a serial version id.
@SuppressWarnings("serial")
public class UserSpacePermissionsFilter extends Filter {
    private Filter inSpaceFilter;
    private SpacePermissionsFilterDao dao;
    private User currentUser;

    public UserSpacePermissionsFilter(
            SpacePermissionsFilterDao spacePermissionsFilterDao,
            Filter inSpaceFilter, User currentUser) {
        this.dao = spacePermissionsFilterDao;
        this.inSpaceFilter = inSpaceFilter;
        this.currentUser = currentUser;
    }

    public BitSet bits(IndexReader reader) throws IOException {
        Filter permittedSpacesFilter = createFilterForUser();

        BitSet spaceFilterResult = permittedSpacesFilter.bits(reader);
        spaceFilterResult.or(inSpaceFilter.bits(reader));
        return spaceFilterResult;
    }

    /**
     * Decides to filter based on spaces the user does or does not have access.
     * 
     * This is decided by which space is larger and negating the filter if
     * necessary.
     * 
     * @return the filter to be used for space permission filtering
     */
    private Filter createFilterForUser() {
        List<String> permittedSpaceKeys =
                dao.getPermittedSpaceKeysForUser(currentUser);
        List<String> unpermittedSpaceKeys =
                dao.getUnPermittedSpaceKeysForUser(currentUser);

        boolean negatingFlag = false;
        Collection<String> spaceKeys;
        if (unpermittedSpaceKeys.size() < permittedSpaceKeys.size()) {
            negatingFlag = true;
            spaceKeys = unpermittedSpaceKeys;
        } else {
            spaceKeys = permittedSpaceKeys;
        }

        MultiTermFilter spaceFilter = new MultiTermFilter(negatingFlag);
        for (String spaceKey : spaceKeys) {
            spaceFilter.addTerm(new Term(DocumentFieldName.SPACE_KEY, spaceKey));
        }

        return spaceFilter;
    }
}
