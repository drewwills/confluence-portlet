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
/**
 * Implements all the <code>WikiService</code>s using the Confluence API.
 * <p>
 * If the action needs a manager object from Conflunece look at
 * {@link edu.uiuc.cites.portal.wiki.plugin.guice}.
 * <p>
 * All actions have a corresponding servlet in the parent package.
 * <p>
 * This package has the code most likely to break since it recreates what
 * Confluence presents to users and often Confluence does this ignorant of the
 * idea that others may want these results. For each action, if code requires
 * Confluence references, they will be provided in the comments.
 */
@DefaultAnnotation(NonNull.class)
package edu.illinois.my.wiki.plugin.servlet.action;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation;
import edu.umd.cs.findbugs.annotations.NonNull;

