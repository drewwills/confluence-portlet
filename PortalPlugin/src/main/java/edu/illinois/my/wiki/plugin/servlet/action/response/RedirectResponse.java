package edu.illinois.my.wiki.plugin.servlet.action.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

final class RedirectResponse implements ServletActionResponse {
    private final String confluenceUrl;

    private static final String contextUrl = "/wiki";

    RedirectResponse(String confluenceUrl) {
        this.confluenceUrl = confluenceUrl;
    }

    @Override
    public void send(HttpServletResponse httpResponse) throws IOException {
        String redirectUrl = contextUrl + confluenceUrl;
        httpResponse.sendRedirect(redirectUrl);
    }
}
