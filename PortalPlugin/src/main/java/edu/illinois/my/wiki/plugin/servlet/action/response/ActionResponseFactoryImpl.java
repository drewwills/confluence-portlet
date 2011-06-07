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
package edu.illinois.my.wiki.plugin.servlet.action.response;

import java.io.Serializable;

import com.google.inject.Inject;
import com.google.inject.Provider;

import edu.illinois.my.wiki.services.streaming.ObjectStreamer;
import edu.illinois.my.wiki.services.streaming.data.ExceptionOption;
import edu.illinois.my.wiki.services.streaming.data.FailureOption;
import edu.illinois.my.wiki.services.streaming.data.SuccessOption;

final class ActionResponseFactoryImpl implements ActionResponseFactory {
    private final Provider<ObjectStreamer> streamer;

    @Inject
    ActionResponseFactoryImpl(Provider<ObjectStreamer> streamer) {
        this.streamer = streamer;
    }

    @Override
    public ServletActionResponse createError(int errorCode, String errorMessage) {
        return new ErrorResponse(errorCode, errorMessage);
    }

    @Override
    public ServletActionException createException(String reason, int errorCode,
            String errorMessage) {
        ErrorResponse errorResponse =
                new ErrorResponse(errorCode, errorMessage);
        return new ServletActionException(reason, errorResponse);
    }

    @Override
    public ServletActionResponse createRedirect(String confluenceUrl) {
        return new RedirectResponse(confluenceUrl);
    }

    @Override
    public ServletActionResponse createSendException(RuntimeException exception) {
        ExceptionOption<?> objectResponse =
                new FailureOption<RuntimeException>(exception);
        return new SendExceptionOptionResponse(objectResponse, streamer.get());
    }

    @Override
    public ServletActionResponse createSendObject(Serializable object) {
        ExceptionOption<?> objectResponse =
                new SuccessOption<Serializable>(object);
        return new SendExceptionOptionResponse(objectResponse, streamer.get());
    }
}
