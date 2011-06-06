package edu.illinois.my.wiki.portlet.action.result;

import com.google.common.collect.ImmutableList;

import edu.illinois.my.wiki.services.WikiLocation;

public interface ListingResultFactory extends ActionResultFactory {
    ActionResult createListing(ImmutableList<WikiLocation> listing);
}
