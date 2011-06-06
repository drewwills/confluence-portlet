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
