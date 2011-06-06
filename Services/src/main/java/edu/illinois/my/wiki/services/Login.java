package edu.illinois.my.wiki.services;

public final class Login implements WikiService<Redirect> {
    public static final String LOCATION = "location";

    private static final String URL = "/login";

    public Login() {}

    @Override
    public String getUrl() {
        return URL;
    }
}
