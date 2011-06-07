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
import java.io.InputStream;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import edu.illinois.my.helpers.PropertiesLoader;
import edu.illinois.my.names.Username;
import edu.illinois.my.names.UsernameImpl;
import edu.illinois.my.wiki.portlet.action.ActionFactory;
import edu.illinois.my.wiki.portlet.action.PortletAction;
import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.illinois.my.wiki.services.parameters.Parameters;
import edu.illinois.my.wiki.services.parameters.ParametersBuilder;
import edu.illinois.my.wiki.services.parameters.ParametersBuilderImpl;

/**
 * Provides an entry-point for PortletActions.
 */
public final class WikiPortlet extends GenericPortlet {
    private ActionResultSessionFactory sessionFactory;
    private ActionFactory actionFactory;
    private PortletActionExecutor actionExecutor;
    private PortletRenderer portletRenderer;

    private static final String SERVER_URL_LOCATION =
            "/WEB-INF/serverURLProperties.xml";
    private static final String ERROR_MESSAGES_LOCATION =
            "/WEB-INF/errorMessages.xml";
    private static final String BRANDING_MESSAGES_LOCATION =
            "/WEB-INF/brandingMessages.xml";

    @Override
    public void init() {
        PortletContext context = getPortletContext();
        Injector injector = getInjector(context);
        sessionFactory = injector.getInstance(ActionResultSessionFactory.class);
        actionExecutor = injector.getInstance(PortletActionExecutor.class);
        actionFactory = injector.getInstance(ActionFactory.class);
        portletRenderer = getPortletRenderer(context);
    }

    private Injector getInjector(PortletContext context) {
        ImmutableMap<String, String> serverUrlProperties =
                loadPropertiesMap(SERVER_URL_LOCATION, context);
        ImmutableMap<String, String> errorMessagesProperties =
                loadPropertiesMap(ERROR_MESSAGES_LOCATION, context);
        Module guiceConfigurations =
                new PortletWiring(serverUrlProperties, errorMessagesProperties);
        return Guice.createInjector(guiceConfigurations);
    }

    private PortletRenderer getPortletRenderer(PortletContext context) {
        ImmutableMap<String, String> brandingMessages =
                loadPropertiesMap(BRANDING_MESSAGES_LOCATION, context);
        return new PortletRendererImpl(brandingMessages, context);
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        Username username = getRemoteUser(request);
        ActionResultSession session = getSession(request);
        ActionResult result = getResult(username, session);
        portletRenderer.doView(result, request, response);
    }

    private ActionResult getResult(Username username,
            ActionResultSession session) {
        if (session.hasResult()) {
            return session.getResult();
        } else {
            PortletAction<?> firstVisitAction =
                    actionFactory.createFirstVisitAction();
            return actionExecutor.processAction(emptyParameters(), username,
                    firstVisitAction);
        }
    }

    private Parameters emptyParameters() {
        ParametersBuilder builder = new ParametersBuilderImpl();
        return builder.build();
    }

    @Override
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException, IOException {
        Parameters requestParameters = getRequestParameters(request);
        String actionParameter = requestParameters.get(ViewParameters.ACTION);
        PortletAction<?> action =
                actionFactory.create(actionParameter, response);
        Username username = getRemoteUser(request);
        ActionResult actionResult =
                actionExecutor.processAction(requestParameters, username,
                        action);

        ActionResultSession session = getSession(request);
        session.setResult(actionResult);
    }

    @Override
    public void doHelp(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        portletRenderer.doHelp(request, response);
    }

    private ImmutableMap<String, String> loadPropertiesMap(String fileLocation,
            PortletContext context) {
        InputStream fileStream = context.getResourceAsStream(fileLocation);
        return PropertiesLoader.loadAsMap(fileStream);
    }

    private Parameters getRequestParameters(ActionRequest request) {
        @SuppressWarnings("unchecked")
        Map<String, String[]> parameterMap = request.getParameterMap();
        ParametersBuilder builder = new ParametersBuilderImpl();
        builder.addFirstValues(parameterMap);
        return builder.build();
    }

    private Username getRemoteUser(PortletRequest request) {
        String username = request.getRemoteUser();
        if (!isEmpty(username)) {
            return new UsernameImpl(username);
        } else {
            return new UsernameImpl();
        }
    }

    private boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    private ActionResultSession getSession(PortletRequest request) {
        PortletSession session = request.getPortletSession();
        return sessionFactory.create(session);
    }
}
