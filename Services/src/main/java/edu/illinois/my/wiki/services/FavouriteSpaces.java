package edu.illinois.my.wiki.services;

public final class FavouriteSpaces implements WikiLocationsService {
    private final static String URL = "/favouritespaces";

    public FavouriteSpaces() {}

    @Override
    public String getUrl() {
        return URL;
    }
}
