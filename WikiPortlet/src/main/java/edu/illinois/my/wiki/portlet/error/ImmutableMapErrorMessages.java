package edu.illinois.my.wiki.portlet.error;

import com.google.common.collect.ImmutableMap;

public final class ImmutableMapErrorMessages implements ErrorMessages {
    private final ImmutableMap<String, String> errorMessages;

    public ImmutableMapErrorMessages(ImmutableMap<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    public String get(ErrorMessageKey errorKey) {
        String mapKey = errorKey.toString();
        if (errorMessages.containsKey(mapKey)) {
            return errorMessages.get(mapKey);
        } else {
            throw new IllegalArgumentException(
                    "The following error key does not have a message: "
                            + errorKey);
        }
    }
}
