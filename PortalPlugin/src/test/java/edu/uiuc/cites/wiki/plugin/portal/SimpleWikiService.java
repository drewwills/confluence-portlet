package edu.uiuc.cites.wiki.plugin.portal;

import edu.illinois.my.wiki.services.WikiService;

public class SimpleWikiService implements WikiService<String> {

    private final String URL = "URL";

    @Override
    public String getUrl() {
        return URL;
    }
}
