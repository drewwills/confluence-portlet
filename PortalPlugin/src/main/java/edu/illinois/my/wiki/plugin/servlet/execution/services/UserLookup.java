package edu.illinois.my.wiki.plugin.servlet.execution.services;

import com.atlassian.user.User;

import edu.illinois.my.names.Username;

public interface UserLookup {
    User getUser(Username username);
}
