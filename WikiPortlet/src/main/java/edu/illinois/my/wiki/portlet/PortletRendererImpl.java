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

import java.io.IOException;

import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.google.common.collect.ImmutableMap;

import edu.illinois.my.wiki.portlet.action.result.ActionResult;

final class PortletRendererImpl implements PortletRenderer {
    private final ImmutableMap<String, String> brandingMessages;
    private final PortletContext context;

    private static final String CONTENT_TYPE = "text/html";
    private static final String HELP_LOCATION = "/help.jsp";

    PortletRendererImpl(ImmutableMap<String, String> brandingMessages,
            PortletContext context) {
        this.brandingMessages = brandingMessages;
        this.context = context;
    }

    @Override
    public void doView(ActionResult actionResult, RenderRequest request,
            RenderResponse response) throws PortletException, IOException {
        response.setContentType(CONTENT_TYPE);
        request.setAttribute(ViewParameters.BRANDING_MESSAGES, brandingMessages);
        String jsp = actionResult.sendToJsp(request);
        context.getRequestDispatcher(jsp).include(request, response);
    }

    @Override
    public void doHelp(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        response.setContentType(CONTENT_TYPE);
        context.getRequestDispatcher(HELP_LOCATION).include(request, response);
    }
}
