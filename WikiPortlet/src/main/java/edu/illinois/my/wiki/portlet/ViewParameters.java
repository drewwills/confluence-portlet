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
package edu.illinois.my.wiki.portlet;

public final class ViewParameters {
    // // View Input
    // Action Key
    public static final String ACTION = "Action";
    // Actions
    public static final String RECENTLY_UPDATED = "RecentlyUpdated";
    public static final String FAVOURITE_SPACES = "FavouriteSpaces";
    public static final String FAVOURITE_PAGES = "FavouritePages";
    public static final String LOGIN = "Login";
    public static final String SEARCH = "Search";
    // LOCATION is a Login action parameter
    public static final String LOCATION = "Location";
    // QUERY is a Search action parameter
    public static final String QUERY = "Query";

    // // View Output
    public static final String ERROR = "Error";
    public static final String BRANDING_MESSAGES = "brandingMessages";
    public static final String LISTINGS = "Listings";
    // SEARCH_TERM is used on a search action
    public static final String SEARCH_TERM = "searchTerm";

    private ViewParameters() {}
}
