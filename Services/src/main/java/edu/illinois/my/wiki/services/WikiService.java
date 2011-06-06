package edu.illinois.my.wiki.services;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

public interface WikiService<T extends Serializable> {
    // URL paths
    String CONTEXT_PATH = "/wiki";
    String PORTAL_SERVLETS_PATH = "/portal";
    // Parameter keys
    String USERNAME = "username";
    String KEY = "key";
    // Response Types
    int BAD_REQUEST = HttpServletResponse.SC_BAD_REQUEST;
    int NO_USER_ERROR = HttpServletResponse.SC_CONFLICT;
    int SERVICE_ERROR = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    int CALLBACK_ERROR = HttpServletResponse.SC_BAD_GATEWAY;
    int SUCCESS = HttpServletResponse.SC_OK;

    String getUrl();
}
