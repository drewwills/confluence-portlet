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
package edu.illinois.my.wiki.portlet.error;

public final class ErrorKeys {
    public static final ErrorMessageKey NO_WIKI_ACCOUNT =
            new ErrorMessageKeyImpl("noWikiAccount");
    public static final ErrorMessageKey RETRY =
            new ErrorMessageKeyImpl("retry");
    public static final ErrorMessageKey PORTAL =
            new ErrorMessageKeyImpl("portal");
    public static final ErrorMessageKey CALLBACK =
            new ErrorMessageKeyImpl("callback");
    public static final ErrorMessageKey BLANK_QUERY =
            new ErrorMessageKeyImpl("blankQuery");
    public static final ErrorMessageKey STAR_QUERY =
            new ErrorMessageKeyImpl("starQuery");

    private ErrorKeys() {}
}
