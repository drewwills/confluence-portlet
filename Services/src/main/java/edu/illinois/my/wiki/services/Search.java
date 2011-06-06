package edu.illinois.my.wiki.services;

public class Search implements WikiLocationsService {
    public static final String QUERY = "query";

    private final static String URL = "/search";

    public Search() {}

    @Override
    public String getUrl() {
        return URL;
    }
}
