package edu.illinois.my.wiki.services.wrappers;

public interface LoggerInterface {
    void error(String error);

    void error(String error, Throwable exception);
}
