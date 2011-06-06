package edu.illinois.my.wiki.portlet.action;

import javax.portlet.ActionResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.internal.ImmutableMap;

import edu.illinois.my.wiki.portlet.ViewParameters;
import edu.illinois.my.wiki.portlet.action.result.ActionResultFactory;
import edu.illinois.my.wiki.portlet.action.result.RedirectResultFactory;
import edu.illinois.my.wiki.portlet.http.SecureUriGenerator;
import edu.illinois.my.wiki.services.FavouritePages;
import edu.illinois.my.wiki.services.FavouriteSpaces;
import edu.illinois.my.wiki.services.RecentlyUpdated;
import edu.illinois.my.wiki.services.WikiLocationsService;

final class ActionFactoryImpl implements ActionFactory {
    private final Provider<SecureUriGenerator> uriGeneratorProvider;
    private final Provider<ActionResultFactory> resultFactoryProvider;
    private final Provider<SearchAction> searchActionProvider;
    private final SimpleListingActionFactory simpleListingActionFactory;

    private static final SimpleListingMap simpleActions = createSimpleActions();
    private static final String RECENTLY_UPDATED_JSP = "/recentlyUpdated.jsp";
    private static final String FAVOURITE_SPACES_JSP = "/favouriteSpaces.jsp";
    private static final String FAVOURITE_PAGES_JSP = "/favouritePages.jsp";
    private static final String WRONG_ACTION_KEY =
            "The portlet action key was incorrectly set to: ";

    @Inject
    ActionFactoryImpl(Provider<SecureUriGenerator> uriGeneratorProvider,
            Provider<ActionResultFactory> resultFactoryProvider,
            Provider<SearchAction> searchActionProvider,
            SimpleListingActionFactory simpleListingActionFactory) {
        this.uriGeneratorProvider = uriGeneratorProvider;
        this.resultFactoryProvider = resultFactoryProvider;
        this.searchActionProvider = searchActionProvider;
        this.simpleListingActionFactory = simpleListingActionFactory;
    }

    @Override
    public PortletAction<?> create(String actionRequestParameter,
            ActionResponse portletActionResponse) {
        if (isSimpleListingAction(actionRequestParameter)) {
            return createSimpleListingAction(actionRequestParameter);
        } else if (isSearchAction(actionRequestParameter)) {
            return searchActionProvider.get();
        } else if (isLoginAction(actionRequestParameter)) {
            return createLoginAction(portletActionResponse);
        } else {
            return createErrorAction(actionRequestParameter);
        }
    }

    @Override
    public PortletAction<?> createFirstVisitAction() {
        return createSimpleListingAction(ViewParameters.RECENTLY_UPDATED);
    }

    private static SimpleListingMap createSimpleActions() {
        ImmutableMap.Builder<String, SimpleListingData<? extends WikiLocationsService>> builder =
                ImmutableMap.builder();
        declareSimpleActions(builder);
        return new SimpleListingMap(builder.build());
    }

    private static
            void
            declareSimpleActions(
                    ImmutableMap.Builder<String, SimpleListingData<? extends WikiLocationsService>> builder) {
        builder.put(ViewParameters.RECENTLY_UPDATED,
                SimpleListingData.createData(new RecentlyUpdated(),
                        RECENTLY_UPDATED_JSP));
        builder.put(ViewParameters.FAVOURITE_SPACES,
                SimpleListingData.createData(new FavouriteSpaces(),
                        FAVOURITE_SPACES_JSP));
        builder.put(ViewParameters.FAVOURITE_PAGES,
                SimpleListingData.createData(new FavouritePages(),
                        FAVOURITE_PAGES_JSP));
    }

    private boolean isSearchAction(String actionRequestParameter) {
        return ViewParameters.SEARCH.equals(actionRequestParameter);
    }

    private boolean isLoginAction(String actionRequestParameter) {
        return ViewParameters.LOGIN.equals(actionRequestParameter);
    }

    private PortletAction<?> createLoginAction(
            ActionResponse portletActionResponse) {
        ActionResultFactory resultFactory = resultFactoryProvider.get();
        RedirectResultFactory redirectFactory =
                resultFactory.createRedirectFactory(portletActionResponse);
        return new LoginAction(uriGeneratorProvider.get(), redirectFactory);
    }

    private PortletAction<?> createErrorAction(String actionRequestParameter) {
        String logMessage = WRONG_ACTION_KEY + actionRequestParameter;
        ActionResultFactory resultFactory = resultFactoryProvider.get();
        return new PortalErrorAction(logMessage, resultFactory);
    }

    private boolean isSimpleListingAction(String actionRequestParameter) {
        return simpleActions.containsKey(actionRequestParameter);
    }

    private WikiLocationsAction<?> createSimpleListingAction(
            String actionRequestParameter) {
        SimpleListingData<? extends WikiLocationsService> listingData =
                simpleActions.get(actionRequestParameter);
        WikiLocationsService service = listingData.getService();
        String jsp = listingData.getJsp();
        return simpleListingActionFactory.create(service, jsp);
    }

    private static final class SimpleListingMap {
        private final ImmutableMap<String, SimpleListingData<? extends WikiLocationsService>> map;

        private SimpleListingMap(
                ImmutableMap<String, SimpleListingData<? extends WikiLocationsService>> map) {
            this.map = map;
        }

        private boolean containsKey(String key) {
            return map.containsKey(key);
        }

        private SimpleListingData<? extends WikiLocationsService>
                get(String key) {
            return map.get(key);
        }
    }

    private static final class SimpleListingData<W extends WikiLocationsService> {
        private final W service;
        private final String jsp;

        private SimpleListingData(W service, String jsp) {
            this.service = service;
            this.jsp = jsp;
        }

        private W getService() {
            return service;
        }

        private String getJsp() {
            return jsp;
        }

        private static <S extends WikiLocationsService> SimpleListingData<S>
                createData(S service, String jsp) {
            return new SimpleListingData<S>(service, jsp);
        }
    }
}
