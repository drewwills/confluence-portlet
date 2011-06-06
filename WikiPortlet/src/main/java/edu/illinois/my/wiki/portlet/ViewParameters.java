package edu.illinois.my.wiki.portlet;

public final class ViewParameters {
    // // View Input
    // Action Key
    public static final String ACTION = "Action";
    // Actions
    public static final String RECENTLY_UPDATED = "RecentlyUpdated";
    public static final String FAVOURITE_SPACES = "FavouriteSpaces";
    public static final String FAVOURITE_PAGES = "FavouritePages";
    public static final String LOGIN = "Login";
    public static final String SEARCH = "Search";
    // LOCATION is a Login action parameter
    public static final String LOCATION = "Location";
    // QUERY is a Search action parameter
    public static final String QUERY = "Query";

    // // View Output
    public static final String ERROR = "Error";
    public static final String BRANDING_MESSAGES = "brandingMessages";
    public static final String LISTINGS = "Listings";
    // SEARCH_TERM is used on a search action
    public static final String SEARCH_TERM = "searchTerm";

    private ViewParameters() {}
}
