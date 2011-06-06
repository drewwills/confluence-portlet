package edu.illinois.my.wiki.plugin.servlet.execution.services;

import edu.illinois.my.names.Username;
import edu.illinois.my.security.keys.RequestKey;

public interface Validator {
    void validate(Username username, RequestKey key, String ip);
}
