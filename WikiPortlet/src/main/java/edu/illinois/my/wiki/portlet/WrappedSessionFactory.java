package edu.illinois.my.wiki.portlet;

import javax.portlet.PortletSession;

import com.google.inject.Inject;

final class WrappedSessionFactory implements ActionResultSessionFactory {

    @Inject
    WrappedSessionFactory() {
    }

    @Override
    public ActionResultSession create(PortletSession session) {
        return new WrappedSession(session);
    }
}
