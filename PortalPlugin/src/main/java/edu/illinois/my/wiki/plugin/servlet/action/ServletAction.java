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

import com.atlassian.user.User;

import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionException;
import edu.illinois.my.wiki.plugin.servlet.action.response.ServletActionResponse;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.parameters.Parameters;

/**
 * Implements the specified <code>WikiService</code>.
 * 
 * @param <S> the service that will be performed
 * @param <T> the possible data result of that service
 */
public interface ServletAction<W extends WikiService<?>> {
    /**
     * Performs the service.
     * 
     * @param user for whom the action is performed
     * @param requestParameters possible settings for the action
     * @return response to be transmitted
     * @throws ServletActionException when a known error happens and an error
     *         code should be sent
     * @throws RuntimeException when an unrecoverable error happens
     */
    ServletActionResponse createResponse(User user, Parameters requestParameters)
            throws ServletActionException;
}
