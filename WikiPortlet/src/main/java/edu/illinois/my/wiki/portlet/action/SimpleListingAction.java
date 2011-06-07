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
package edu.illinois.my.wiki.portlet.action;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.action.result.ListingResultFactory;
import edu.illinois.my.wiki.portlet.http.Communicator;
import edu.illinois.my.wiki.services.WikiLocationsService;
import edu.illinois.my.wiki.services.parameters.Parameters;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;
import edu.illinois.my.wiki.services.parameters.ParametersBuilderImpl;

final class SimpleListingAction<W extends WikiLocationsService> implements
        WikiLocationsAction<W> {
    private final W service;
    private final String jsp;
    private final Communicator communicator;
    private final ActionResultFactory resultFactory;

    SimpleListingAction(W service, String jsp, Communicator communicator,
            ActionResultFactory resultFactory) {
        this.service = service;
        this.jsp = jsp;
        this.communicator = communicator;
        this.resultFactory = resultFactory;
    }

    @Override
    public ActionResult execute(Parameters renderParameters, Username username) {
        ListingResultFactory listingBuilder =
                resultFactory.createListingFactory(jsp);
        ParametersBuilder emptyWikiParameters = new ParametersBuilderImpl();
        return communicator.requestListing(username, listingBuilder, service,
                emptyWikiParameters);
    }
}
