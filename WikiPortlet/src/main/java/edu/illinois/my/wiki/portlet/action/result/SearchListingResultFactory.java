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
package edu.illinois.my.wiki.portlet.action.result;

import javax.portlet.ActionResponse;

import com.google.common.collect.ImmutableList;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.error.ErrorMessageKey;
import edu.illinois.my.wiki.services.WikiLocation;

final class SearchListingResultFactory implements ListingResultFactory {
    private final String searchTerm;
    private final String searchJsp;
    private final ActionResultFactory resultFactory;

    SearchListingResultFactory(String searchTerm, String searchJsp,
            ActionResultFactory resultFactory) {
        this.searchTerm = searchTerm;
        this.searchJsp = searchJsp;
        this.resultFactory = resultFactory;
    }

    @Override
    public ActionResult createListing(ImmutableList<WikiLocation> listing) {
        AnyJspListingResult listingResult =
                new AnyJspListingResult(listing, searchJsp);
        return new SearchListingResult(searchTerm, listingResult);
    }

    @Override
    public ActionResult createErrorMessage(ErrorMessageKey userErrorKey) {
        return resultFactory.createErrorMessage(userErrorKey);
    }

    @Override
    public ActionResult createException(Username username, Exception exception) {
        return resultFactory.createException(username, exception);
    }

    @Override
    public ActionResult createLoggedError(ErrorMessageKey userErrorKey,
            String logMessage) {
        return resultFactory.createLoggedError(userErrorKey, logMessage);
    }

    @Override
    public ActionResult createWikiError(Username username, int statusCode,
            String statusText) {
        return resultFactory.createWikiError(username, statusCode, statusText);
    }

    @Override
    public ActionResult createPortalException(String logMessage,
            Exception exception) {
        return resultFactory.createPortalException(logMessage, exception);
    }

    @Override
    public ListingResultFactory createListingFactory(String jsp) {
        return resultFactory.createListingFactory(jsp);
    }

    @Override
    public RedirectResultFactory createRedirectFactory(
            ActionResponse actionResponse) {
        return resultFactory.createRedirectFactory(actionResponse);
    }

    @Override
    public ListingResultFactory createSearchFactory(String searchTerm,
            String jsp) {
        return resultFactory.createSearchFactory(searchTerm, jsp);
    }

    @Override
    public ActionResult createFirstVisit() {
        return resultFactory.createFirstVisit();
    }
}
