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
package edu.illinois.my.wiki.portlet.http;

import edu.illinois.my.helpers.ExposingBindModule;
import edu.illinois.my.wiki.portlet.http.handler.HandlerWiring;
import edu.illinois.my.wiki.portlet.http.services.ServicesWiring;
import edu.illinois.my.wiki.portlet.http.services.WikiServer;

public final class HttpWiring extends ExposingBindModule {
    private final WikiServer wikiServer;

    public HttpWiring(WikiServer wikiServer) {
        this.wikiServer = wikiServer;
    }

    @Override
    protected void configure() {
        install(new ServicesWiring(wikiServer));

        install(new HandlerWiring());

        exposingBind(SecureUriGenerator.class, RegisteringUriGenerator.class);
        exposingBind(Communicator.class, HttpClientCommunicator.class);
    }
}
