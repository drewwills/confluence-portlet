package edu.illinois.my.wiki.services.streaming.data;

import java.io.Serializable;

public interface ExceptionOption<T extends Serializable> extends Serializable {
    boolean wasExceptionThrown();

    Exception getException();

    T getObject();
}
