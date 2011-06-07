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

import org.apache.log4j.Logger;

import edu.illinois.my.helpers.ExposingBindModule;
import edu.illinois.my.wiki.portlet.WikiPortlet;
import edu.illinois.my.wiki.portlet.error.ErrorMessages;
import edu.illinois.my.wiki.services.wrappers.Log4jWrapper;
import edu.illinois.my.wiki.services.wrappers.LoggerInterface;

public final class ResultWiring extends ExposingBindModule {
    private final ErrorMessages errorMessages;

    public ResultWiring(ErrorMessages errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    protected void configure() {
        bind(ErrorMessages.class).toInstance(errorMessages);
        bind(LoggerInterface.class).toInstance(wrappedLog4j());
        exposingBind(ActionResultFactory.class, ActionResultFactoryImpl.class);
    }

    private LoggerInterface wrappedLog4j() {
        return new Log4jWrapper(Logger.getLogger(WikiPortlet.class));
    }
}
