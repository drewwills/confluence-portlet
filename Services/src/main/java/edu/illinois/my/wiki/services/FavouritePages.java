package edu.illinois.my.wiki.services;

public final class FavouritePages implements WikiLocationsService {
    private final static String URL = "/favouritepages";

    public FavouritePages() {}

    @Override
    public String getUrl() {
        return URL;
    }
}
