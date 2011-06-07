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

import java.io.IOException;
import java.net.URI;

import javax.portlet.ActionResponse;

final class RedirectResultFactoryImpl implements RedirectResultFactory {
    private final ActionResponse actionResponse;
    private final ActionResultFactory resultFactory;

    private static final String LOG_MESSAGE =
            "The following URL failed to redirect the user: ";

    RedirectResultFactoryImpl(ActionResponse actionResponse,
            ActionResultFactory resultFactory) {
        this.actionResponse = actionResponse;
        this.resultFactory = resultFactory;
    }

    @Override
    public ActionResult create(URI redirectUri) {
        String redirectUrl = redirectUri.toString();
        try {
            actionResponse.sendRedirect(redirectUrl);
            return resultFactory.createFirstVisit();
        } catch (IOException exception) {
            String logMessages = LOG_MESSAGE + redirectUrl;
            return resultFactory.createPortalException(logMessages, exception);
        }
    }
}
