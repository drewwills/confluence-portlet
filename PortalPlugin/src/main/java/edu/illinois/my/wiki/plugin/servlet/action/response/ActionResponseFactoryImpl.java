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
