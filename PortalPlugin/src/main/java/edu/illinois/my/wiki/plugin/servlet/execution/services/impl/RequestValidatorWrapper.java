package edu.illinois.my.wiki.plugin.servlet.execution.services.impl;

import com.google.inject.Inject;

import edu.illinois.my.names.Username;
import edu.illinois.my.security.RequestValidator;
import edu.illinois.my.security.keys.RequestKey;
import edu.illinois.my.wiki.plugin.servlet.action.response.ActionResponseFactory;
import edu.illinois.my.wiki.plugin.servlet.execution.services.Validator;
import edu.illinois.my.wiki.services.WikiService;

/**
 * Validates request using classes from the <code>callback</code> package.
 */
final class RequestValidatorWrapper implements Validator {
    private final RequestValidator callbackRequestValidator;
    private final ActionResponseFactory responseFactory;

    /*
     * Jeff's address and mine
     */
    /*
     * private static final ImmutableList<String> safeAddresses =
     * ImmutableList.of("192.17.26.112", "98.193.26.46");
     */
    private static final String BAD_REQUEST_LOG =
            "The username or key was not sent or was empty.";
    private static final String BAD_REQUEST_OUTGOING =
            "Blank credentials were sent.";
    private static final String CALLBACK_REJECTION_LOG =
            "The key was found to be invalid.";
    private static final String CALLBACK_REJECTION_OUTGOING =
            "The request could not be verified.";

    @Inject
    RequestValidatorWrapper(RequestValidator callbackRequestValidator,
            ActionResponseFactory responseFactory) {
        this.callbackRequestValidator = callbackRequestValidator;
        this.responseFactory = responseFactory;
    }

    @Override
    public void validate(Username username, RequestKey key, String ip) {
        if (username.isEmpty() || key.isEmpty()) {
            throw responseFactory.createException(BAD_REQUEST_LOG,
                    WikiService.BAD_REQUEST, BAD_REQUEST_OUTGOING);
        }
        /*
         * if (safeAddresses.contains(ip)) { return; }
         */
        boolean isValidRequest =
                callbackRequestValidator.isValid(username, key);
        if (!isValidRequest) {
            throw responseFactory.createException(CALLBACK_REJECTION_LOG,
                    WikiService.CALLBACK_ERROR, CALLBACK_REJECTION_OUTGOING);
        }
    }
}
