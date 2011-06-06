package edu.illinois.my.wiki.portlet.error;

import edu.illinois.my.names.StringWrapper;

final class ErrorMessageKeyImpl extends StringWrapper implements
        ErrorMessageKey {
    ErrorMessageKeyImpl(String string) {
        super(string);
    }
}
