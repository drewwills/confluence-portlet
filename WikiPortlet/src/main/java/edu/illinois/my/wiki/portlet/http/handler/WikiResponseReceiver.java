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

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.google.common.collect.ImmutableList;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.portlet.action.result.ListingResultFactory;
import edu.illinois.my.wiki.services.WikiLocation;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.streaming.ObjectStreamer;
import edu.illinois.my.wiki.services.streaming.data.ExceptionOption;
import edu.illinois.my.wiki.services.wrappers.InputStreamInterface;
import edu.illinois.my.wiki.services.wrappers.InputStreamWrapper;

final class WikiResponseReceiver implements ResponseHandler<ActionResult> {
    private final Username username;
    private final ListingResultFactory resultFactory;
    private final ObjectStreamer streamer;

    WikiResponseReceiver(Username username, ListingResultFactory resultFactory,
            ObjectStreamer streamer) {
        this.username = username;
        this.resultFactory = resultFactory;
        this.streamer = streamer;
    }

    @Override
    public ActionResult handleResponse(HttpResponse response)
            throws ClientProtocolException, IOException {
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == WikiService.SUCCESS) {
            return createFromSentObject(response);
        } else {
            String reasonPhrase = statusLine.getReasonPhrase();
            return resultFactory.createWikiError(username, statusCode,
                    reasonPhrase);
        }
    }

    private ActionResult createFromSentObject(HttpResponse response)
            throws IOException {
        InputStreamInterface responseStream = getContentStream(response);
        ExceptionOption<ImmutableList<WikiLocation>> receivedOption =
                streamer.receiveObject(responseStream);
        return createResult(receivedOption);
    }

    private InputStreamInterface getContentStream(HttpResponse response)
            throws IOException {
        return new InputStreamWrapper(response.getEntity().getContent());
    }

    private ActionResult createResult(
            ExceptionOption<ImmutableList<WikiLocation>> receivedOption) {
        if (!receivedOption.wasExceptionThrown()) {
            ImmutableList<WikiLocation> listing = receivedOption.getObject();
            return resultFactory.createListing(listing);
        } else {
            Exception receivedException = receivedOption.getException();
            return resultFactory.createException(username, receivedException);
        }
    }
}
