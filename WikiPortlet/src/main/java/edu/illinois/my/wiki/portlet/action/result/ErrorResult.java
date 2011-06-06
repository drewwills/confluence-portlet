package edu.illinois.my.wiki.portlet.action.result;

import javax.portlet.RenderRequest;

import edu.illinois.my.wiki.portlet.ViewParameters;

final class ErrorResult implements ActionResult {
    private final String errorMessage;

    private static final String ERROR_JSP = "/error.jsp";

    ErrorResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String sendToJsp(RenderRequest request) {
        request.setAttribute(ViewParameters.ERROR, errorMessage);
        return ERROR_JSP;
    }

    @Override
    public boolean isFirstVisit() {
        return false;
    }
}
