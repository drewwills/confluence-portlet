package edu.illinois.my.wiki.portlet.action;

import edu.illinois.my.wiki.services.WikiLocationsService;

interface SimpleListingActionFactory {
    <W extends WikiLocationsService> WikiLocationsAction<W> create(W service,
            String jsp);
}
