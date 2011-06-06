package edu.illinois.my.wiki.plugin.servlet.action.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface ServletActionResponse {
    void send(HttpServletResponse httpResponse) throws IOException;
}
