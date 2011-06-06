package edu.illinois.my.wiki.portlet.error;

public final class ErrorKeys {
    public static final ErrorMessageKey NO_WIKI_ACCOUNT =
            new ErrorMessageKeyImpl("noWikiAccount");
    public static final ErrorMessageKey RETRY =
            new ErrorMessageKeyImpl("retry");
    public static final ErrorMessageKey PORTAL =
            new ErrorMessageKeyImpl("portal");
    public static final ErrorMessageKey CALLBACK =
            new ErrorMessageKeyImpl("callback");
    public static final ErrorMessageKey BLANK_QUERY =
            new ErrorMessageKeyImpl("blankQuery");
    public static final ErrorMessageKey STAR_QUERY =
            new ErrorMessageKeyImpl("starQuery");

    private ErrorKeys() {}
}
