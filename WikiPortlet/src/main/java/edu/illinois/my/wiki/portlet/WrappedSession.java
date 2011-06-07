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

import javax.portlet.PortletSession;

import edu.illinois.my.wiki.portlet.action.result.ActionResult;
import edu.umd.cs.findbugs.annotations.Nullable;

final class WrappedSession implements ActionResultSession {
    private final PortletSession session;

    private static final String PREVIOUS_RESULT = "previousResult";

    WrappedSession(PortletSession session) {
        this.session = session;
    }

    @Override
    public ActionResult getResult() {
        ActionResult result =
                (ActionResult) session.getAttribute(PREVIOUS_RESULT);
        if (result != null) {
            return result;
        } else {
            throw new IllegalStateException(
                    "No action result was present. Always check hasResult().");
        }
    }

    @Override
    public void setResult(ActionResult actionResult) {
        session.setAttribute(PREVIOUS_RESULT, actionResult);
    }

    @Override
    public boolean hasResult() {
        ActionResult result =
                (ActionResult) session.getAttribute(PREVIOUS_RESULT);
        return !(isFirstVisit(result));
    }

    private boolean isFirstVisit(@Nullable ActionResult result) {
        return result == null || result.isFirstVisit();
    }
}
