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
package edu.illinois.my.wiki.portlet.http.handler;

import org.apache.http.client.ResponseHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ListingResultFactory;
import edu.illinois.my.wiki.services.streaming.ObjectStreamer;

final class WikiResponseReceiverFactoryImpl implements
        WikiResponseReceiverFactory {
    private final Provider<ObjectStreamer> streamerProvider;

    @Inject
    WikiResponseReceiverFactoryImpl(Provider<ObjectStreamer> streamerProvider) {
        this.streamerProvider = streamerProvider;
    }

    @Override
    public ResponseHandler<ActionResult> create(Username username,
            ListingResultFactory listingResultFactory) {
        return new WikiResponseReceiver(username, listingResultFactory,
                streamerProvider.get());
    }
}
