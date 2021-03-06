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

import com.atlassian.confluence.search.v2.AbstractChainableSearchFilter;
import com.atlassian.user.User;

/**
 * Filters searches based on a given user's access to spaces and pages.
 * <p>
 * This <code>SearchFilter</code> was created since the default Confluence
 * search filter which restricts to what the user can view,
 * <code>SiteSearchPermissionsSearchFilter</code>, will search with the
 * session's user with no way to override that behavior to get a different
 * user's search. This class allows that user to be specified. It is assumed
 * that <code>settingsManager.getGlobalSettings().isSharedMode()</code> is
 * false, which means users' profile pages will be searched.
 * 
 * @see UserPermissionsSearchFilterMapper
 */
public class UserPermissionsSearchFilter extends AbstractChainableSearchFilter {
    private static final String KEY = "userPermissionsFilter";
    private final User user;

    /**
     * @param user search is performed with this user's permissions
     */
    public UserPermissionsSearchFilter(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * @return a string that matches this class' mapper's plugin key.
     */
    public String getKey() {
        return KEY;
    }
}
