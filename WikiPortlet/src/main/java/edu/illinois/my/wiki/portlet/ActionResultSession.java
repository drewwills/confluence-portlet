package edu.illinois.my.wiki.portlet;

import edu.illinois.my.wiki.portlet.action.result.ActionResult;

/**
 * Provides a wrapping of the session that performs type casting.
 */
interface ActionResultSession {
    void setResult(ActionResult actionResult);

    boolean hasResult();

    ActionResult getResult();
}
