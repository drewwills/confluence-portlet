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
package edu.illinois.my.wiki.portlet.http.services;

import com.google.inject.Inject;

import edu.illinois.my.names.Username;
import edu.illinois.my.security.RequestRegistrar;
import edu.illinois.my.security.keys.RequestKey;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;

final class RequestRegistrarWrapper implements Registrar {
    private final RequestRegistrar registrar;

    @Inject
    RequestRegistrarWrapper(RequestRegistrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void addCredentials(Username username,
            ParametersBuilder parametersBuilder) {
        parametersBuilder.add(WikiService.USERNAME, username.asString());
        RequestKey key = registrar.registerRequest(username);
        parametersBuilder.add(WikiService.KEY, key.asString());
    }
}
