package edu.illinois.my.wiki.plugin.servlet;

import com.google.inject.Singleton;

import edu.illinois.my.wiki.plugin.servlet.action.LoginAction;

@Singleton
public final class LoginServlet extends PortalServlet<LoginAction> {
    private static final long serialVersionUID = 1L;
}
