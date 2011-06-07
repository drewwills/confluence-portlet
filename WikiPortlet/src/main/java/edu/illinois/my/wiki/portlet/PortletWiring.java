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

import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;

import edu.illinois.my.security.CallbackModule;
import edu.illinois.my.wiki.portlet.action.PortletActionWiring;
import edu.illinois.my.wiki.portlet.error.ErrorMessages;
import edu.illinois.my.wiki.portlet.error.ImmutableMapErrorMessages;
import edu.illinois.my.wiki.portlet.http.HttpWiring;
import edu.illinois.my.wiki.portlet.http.services.WikiServer;
import edu.illinois.my.wiki.portlet.http.services.WikiServerImpl;

final class PortletWiring extends AbstractModule {
    private final ImmutableMap<String, String> serverUrls;
    private final ImmutableMap<String, String> errorMessages;

    PortletWiring(ImmutableMap<String, String> serverUrls,
            ImmutableMap<String, String> errorMessages) {
        this.serverUrls = serverUrls;
        this.errorMessages = errorMessages;
    }

    @Override
    protected void configure() {
        install(new CallbackModule(serverUrls));
        install(new PortletActionWiring(errorMessages()));
        install(new HttpWiring(wikiServer()));

        bind(PortletActionExecutor.class).to(ErrorHandlingExecutor.class);
        bind(ActionResultSessionFactory.class).to(WrappedSessionFactory.class);
    }

    private WikiServer wikiServer() {
        String wikiServer = serverUrls.get(ServerKeys.WIKI);
        if (wikiServer != null) {
            return new WikiServerImpl(wikiServer);
        } else {
            throw new RuntimeException(
                    "Missing Wiki server url from configuration file.");
        }
    }

    private ErrorMessages errorMessages() {
        return new ImmutableMapErrorMessages(errorMessages);
    }
}
