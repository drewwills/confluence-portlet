package edu.illinois.my.wiki.plugin.servlet.action.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

final class ErrorResponse implements ServletActionResponse {
    private final int errorCode;
    private final String errorMessage;

    ErrorResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public void send(HttpServletResponse httpResponse) throws IOException {
        httpResponse.sendError(errorCode, errorMessage);
    }
}
