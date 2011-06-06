package edu.illinois.my.wiki.portlet.action.result;

import java.net.URI;

public interface RedirectResultFactory {
    ActionResult create(URI redirectUri);
}
