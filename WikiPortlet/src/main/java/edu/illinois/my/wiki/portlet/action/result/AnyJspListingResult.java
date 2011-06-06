package edu.illinois.my.wiki.portlet.action.result;

import javax.portlet.RenderRequest;

import com.google.common.collect.ImmutableList;

import edu.illinois.my.wiki.portlet.ViewParameters;
import edu.illinois.my.wiki.services.WikiLocation;

final class AnyJspListingResult implements ActionResult {
    private final ImmutableList<WikiLocation> listing;
    private final String jsp;

    AnyJspListingResult(ImmutableList<WikiLocation> listing, String jsp) {
        this.listing = listing;
        this.jsp = jsp;
    }

    @Override
    public String sendToJsp(RenderRequest request) {
        request.setAttribute(ViewParameters.LISTINGS, listing);
        return jsp;
    }

    @Override
    public boolean isFirstVisit() {
        return false;
    }
}
