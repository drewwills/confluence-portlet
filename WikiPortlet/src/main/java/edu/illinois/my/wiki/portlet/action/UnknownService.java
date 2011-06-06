package edu.illinois.my.wiki.portlet.action;

import edu.illinois.my.wiki.services.WikiService;

final class UnknownService implements WikiService<UnknownObject> {
    private UnknownService() {}

    @Override
    public String getUrl() {
        throw new IllegalStateException("This class should not be created.");
    }
}
