package edu.illinois.my.wiki.portlet.action.result;

import javax.portlet.RenderRequest;

import edu.illinois.my.wiki.portlet.ViewParameters;

final class SearchListingResult implements ActionResult {
    private final String searchTerm;
    private final AnyJspListingResult listingResult;

    SearchListingResult(String searchTerm, AnyJspListingResult listingResult) {
        this.searchTerm = searchTerm;
        this.listingResult = listingResult;
    }

    @Override
    public String sendToJsp(RenderRequest request) {
        request.setAttribute(ViewParameters.SEARCH_TERM, searchTerm);
        return listingResult.sendToJsp(request);
    }

    @Override
    public boolean isFirstVisit() {
        return false;
    }
}
