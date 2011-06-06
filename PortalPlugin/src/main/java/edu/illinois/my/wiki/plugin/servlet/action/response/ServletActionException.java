package edu.illinois.my.wiki.plugin.servlet.action.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public final class ServletActionException extends RuntimeException implements
        ServletActionResponse {
    private static final long serialVersionUID = 1L;
    private final ErrorResponse errorResponse;

    ServletActionException(String reason, ErrorResponse errorResponse) {
        super(reason);
        this.errorResponse = errorResponse;
    }

    @Override
    public void send(HttpServletResponse httpResponse) throws IOException {
        errorResponse.send(httpResponse);
    }
}
