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

import com.google.inject.Inject;
import com.google.inject.Provider;

import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.http.Communicator;
import edu.illinois.my.wiki.services.WikiLocationsService;

final class SimpleListingActionFactoryImpl implements
        SimpleListingActionFactory {
    private final Provider<Communicator> communicatorProvider;
    private final Provider<ActionResultFactory> resultFactoryProvider;

    @Inject
    SimpleListingActionFactoryImpl(Provider<Communicator> communicatorProvider,
            Provider<ActionResultFactory> resultFactoryProvider) {
        this.communicatorProvider = communicatorProvider;
        this.resultFactoryProvider = resultFactoryProvider;
    }

    @Override
    public <W extends WikiLocationsService> WikiLocationsAction<W> create(
            W service, String jsp) {
        Communicator communicator = communicatorProvider.get();
        ActionResultFactory resultFactory = resultFactoryProvider.get();
        return new SimpleListingAction<W>(service, jsp, communicator,
                resultFactory);
    }
}
