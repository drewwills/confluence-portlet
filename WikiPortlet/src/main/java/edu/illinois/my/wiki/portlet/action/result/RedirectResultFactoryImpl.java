package edu.illinois.my.wiki.portlet.action.result;

import java.io.IOException;
import java.net.URI;

import javax.portlet.ActionResponse;

final class RedirectResultFactoryImpl implements RedirectResultFactory {
    private final ActionResponse actionResponse;
    private final ActionResultFactory resultFactory;

    private static final String LOG_MESSAGE =
            "The following URL failed to redirect the user: ";

    RedirectResultFactoryImpl(ActionResponse actionResponse,
            ActionResultFactory resultFactory) {
        this.actionResponse = actionResponse;
        this.resultFactory = resultFactory;
    }

    @Override
    public ActionResult create(URI redirectUri) {
        String redirectUrl = redirectUri.toString();
        try {
            actionResponse.sendRedirect(redirectUrl);
            return resultFactory.createFirstVisit();
        } catch (IOException exception) {
            String logMessages = LOG_MESSAGE + redirectUrl;
            return resultFactory.createPortalException(logMessages, exception);
        }
    }
}
