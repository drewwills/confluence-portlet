package edu.illinois.my.wiki.services;

public final class RecentlyUpdated implements WikiLocationsService {
    private static final String URL = "/recentlyupdated";

    public RecentlyUpdated() {}

    @Override
    public String getUrl() {
        return URL;
    }
}
