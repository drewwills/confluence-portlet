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
package edu.illinois.my.wiki.plugin.servlet.action;

import com.google.inject.PrivateModule;

import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.action.response.ResponseWiring;
import edu.illinois.my.wiki.plugin.servlet.action.search.SearchWiring;

public final class ActionWiring extends PrivateModule {
    public ActionWiring() {}

    @Override
    protected void configure() {
        install(new ResponseWiring());
        expose(ActionResponseFactory.class);
        install(new SearchWiring());

        exposingBind(FavouritePagesAction.class);
        exposingBind(FavouriteSpacesAction.class);
        exposingBind(LoginAction.class);
        exposingBind(RecentlyUpdatedAction.class);
        exposingBind(SearchAction.class);
    }

    private void exposingBind(Class<?> binding) {
        bind(binding);
        expose(binding);
    }
}
