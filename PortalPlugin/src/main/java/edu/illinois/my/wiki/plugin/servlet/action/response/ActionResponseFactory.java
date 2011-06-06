package edu.illinois.my.wiki.plugin.servlet.action.response;

import java.io.Serializable;

public interface ActionResponseFactory {
    ServletActionResponse createError(int errorCode, String errorMessage);

    ServletActionResponse createSendObject(Serializable object);

    ServletActionResponse createSendException(RuntimeException exception);

    ServletActionResponse createRedirect(String confluenceUrl);

    ServletActionException createException(String reason, int errorCode,
            String errorMessage);
}
