package edu.illinois.my.wiki.services.wrappers;

import org.apache.log4j.Logger;

public final class Log4jWrapper implements LoggerInterface {
    private final Logger log;

    public Log4jWrapper(Logger log) {
        this.log = log;
    }

    @Override
    public void error(String error) {
        log.error(error);
    }

    @Override
    public void error(String error, Throwable exception) {
        log.error(error, exception);
    }
}
