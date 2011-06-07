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
package edu.illinois.my.wiki.portlet.action.result;

import javax.portlet.ActionResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;

import edu.illinois.my.names.Username;
import edu.illinois.my.wiki.portlet.error.ErrorKeys;
import edu.illinois.my.wiki.portlet.error.ErrorMessageKey;
import edu.illinois.my.wiki.portlet.error.ErrorMessages;
import edu.illinois.my.wiki.services.WikiService;
import edu.illinois.my.wiki.services.wrappers.LoggerInterface;

final class ActionResultFactoryImpl implements ActionResultFactory {
    private final ErrorMessages errorMessages;
    private final LoggerInterface log;
    private final Provider<ActionResultFactory> resultFactoryProvider;

    private static final String EXCEPTION_LOG_MESSAGE =
            "An exception occurred on the Wiki or in the portal for the user: ";
    private static final String NO_USER =
            "The following user was not found on the Wiki: ";
    private static final String CALLBACK_FAILED =
            "A callback error was detected for user: ";
    private static final String UNKNOWN_ERROR =
            "An unexpected error has occurred for user: ";
    private static final String ERROR_CODE = " Error Code: ";
    private static final String STATUS_TEXT = " Error Message: ";

    @Inject
    ActionResultFactoryImpl(ErrorMessages errorMessages, LoggerInterface log,
            Provider<ActionResultFactory> resultFactoryProvider) {
        this.errorMessages = errorMessages;
        this.log = log;
        this.resultFactoryProvider = resultFactoryProvider;
    }

    @Override
    public ListingResultFactory createSearchFactory(String searchTerm,
            String jsp) {
        return new SearchListingResultFactory(searchTerm, jsp,
                resultFactoryProvider.get());
    }

    @Override
    public ListingResultFactory createListingFactory(String jsp) {
        return new AnyJspListingResultFactory(jsp, resultFactoryProvider.get());
    }

    @Override
    public RedirectResultFactory createRedirectFactory(
            ActionResponse actionResponse) {
        return new RedirectResultFactoryImpl(actionResponse,
                resultFactoryProvider.get());
    }

    @Override
    public ActionResult createException(Username username, Exception exception) {
        log.error(EXCEPTION_LOG_MESSAGE + username, exception);
        return createErrorMessage(ErrorKeys.RETRY);
    }

    @Override
    public ActionResult createPortalException(String logMessage,
            Exception exception) {
        log.error(logMessage, exception);
        return createErrorMessage(ErrorKeys.RETRY);
    }

    @Override
    public ActionResult createWikiError(Username username, int statusCode,
            String statusText) {
        if (statusCode == WikiService.NO_USER_ERROR) {
            log.error(NO_USER + username);
            return createErrorMessage(ErrorKeys.NO_WIKI_ACCOUNT);
        } else if (statusCode == WikiService.CALLBACK_ERROR) {
            log.error(CALLBACK_FAILED + username);
            return createErrorMessage(ErrorKeys.CALLBACK);
        } else {
            log.error(UNKNOWN_ERROR + username + ERROR_CODE + statusCode
                    + STATUS_TEXT + statusText);
            return createErrorMessage(ErrorKeys.RETRY);
        }
    }

    @Override
    public ActionResult createLoggedError(ErrorMessageKey userErrorKey,
            String logMessage) {
        log.error(logMessage);
        return createErrorMessage(userErrorKey);
    }

    @Override
    public ActionResult createErrorMessage(ErrorMessageKey userErrorKey) {
        String errorMessage = errorMessages.get(userErrorKey);
        return new ErrorResult(errorMessage);
    }

    @Override
    public ActionResult createFirstVisit() {
        return new FirstVisitResult();
    }
}
