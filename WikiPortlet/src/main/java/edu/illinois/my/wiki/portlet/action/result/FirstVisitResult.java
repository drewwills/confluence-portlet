package edu.illinois.my.wiki.portlet.action.result;

import javax.portlet.RenderRequest;

final class FirstVisitResult implements ActionResult {
    private final static String reason =
            "The default action should have been performed for the user.";

    FirstVisitResult() {}

    @Override
    public String sendToJsp(RenderRequest request) {
        throw new UnsupportedOperationException(reason);
    }

    @Override
    public boolean isFirstVisit() {
        return true;
    }
}
