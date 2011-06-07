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
package edu.illinois.my.wiki.plugin;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.atlassian.confluence.labels.LabelManager;
import com.atlassian.confluence.search.v2.SearchManager;
import com.atlassian.confluence.user.UserAccessor;
import com.atlassian.core.util.ClassLoaderUtils;
import com.atlassian.event.EventManager;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

import edu.illinois.my.helpers.PropertiesLoader;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Configures our Guice injector using properties and shares it with Guice.
 */
public final class ServletInitializationAndConfluenceWiring extends
        GuiceServletContextListener {
    @Nullable
    private Injector injector = null;
    private static final String CALLBACK_PROPERTIES_FILENAME =
            "callbackProperties.xml";

    private UserAccessor userAccessor;
    private EventManager eventManager;
    private LabelManager labelManager;
    private SearchManager searchManager;

    /**
     * This method is called by the servlet container.
     * 
     * It initializes Guice from Properties files. If the files cannot be read,
     * an exception is thrown.
     * 
     * @see ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ImmutableMap<String, String> serverConfiguration =
                getCallbackConfigurations();
        Module guiceConfiguration =
                new PluginWiring(userAccessor, eventManager, labelManager,
                        searchManager, serverConfiguration);
        injector = Guice.createInjector(guiceConfiguration);
        super.contextInitialized(servletContextEvent);
    }

    private ImmutableMap<String, String> getCallbackConfigurations() {
        // ClassLoader loader = getClass().getClassLoader();
        InputStream callbackPropertiesStream =
                ClassLoaderUtils.getResourceAsStream(
                        CALLBACK_PROPERTIES_FILENAME, getClass());
        return PropertiesLoader.loadAsMap(callbackPropertiesStream);
    }

    /**
     * This method is called by the Guice Servlet Framework.
     * 
     * It will supply configurations that detail the servlets and the associated
     * servlet classes. It will be called after contextInitialized.
     */
    @Override
    @CheckForNull
    protected Injector getInjector() {
        return injector;
    }

    /**
     * This method is called by Confluence's Spring system.
     * 
     * It will facilitate Guice handling the manager object.
     */
    public void setUserAccessor(UserAccessor userAccessor) {
        this.userAccessor = userAccessor;
    }

    /**
     * This method is called by Confluence's Spring system.
     * 
     * It will facilitate Guice handling the manager object.
     */
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * This method is called by Confluence's Spring system.
     * 
     * It will facilitate Guice handling the manager object.
     */
    public void setLabelManager(LabelManager labelManager) {
        this.labelManager = labelManager;
    }

    /**
     * This method is called by Confluence's Spring system.
     * 
     * It will facilitate Guice handling the manager object.
     */
    public void setSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
    }
}
