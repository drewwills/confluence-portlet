<%@include file="header.jsp" %>

<div class="portlet-tabs-wrapper portlet-container-flex">
    <ul class="portlet-tabs portlet-tabs-left">
    <li>
      <a href='<portlet:actionURL><portlet:param name="Action" value="RecentlyUpdated" /></portlet:actionURL>'>
        Recently Updated
      </a>
    </li>
    <li>
      <a href='<portlet:actionURL><portlet:param name="Action" value="FavouriteSpaces" /></portlet:actionURL>'>
        My Spaces
      </a>
    </li>
    <li>
      <a href='<portlet:actionURL><portlet:param name="Action" value="FavouritePages" /></portlet:actionURL>'>
        Favourite Pages
      </a>
    </li>
        <li class="portlet-tabs-active">
        Search
    </li>
  </ul>
</div>

<form class="portlet-form portlet-container-flex" method='POST' action='<portlet:actionURL><portlet:param name="Action" value="Search" /></portlet:actionURL>'>
    <div class="portlet-inlineLabels">
        <div class="portlet-ctrlHolder">
            <input class="portlet-textInput" type='text' name='Query' id='query' />
            <input type='submit' value='Search' id='search' />
        </div>
    </div>
</form>

<p class="portlet-brief-note">Search results are limited to 20 items. Use the Dashboard link for access to its full search.</p>

<c:if test="${not empty searchTerm}">
  <p>Searching for: <strong>${searchTerm}</strong></p>
  <c:if test="${empty Listings}">
    <p class="portlet-msg-info"><strong>${brandingMessages["noSearchResults"]}</strong></p>
  </c:if>
  <c:if test="${not empty Listings}">
    <%@include file="listing.jsp" %>
  </c:if>
</c:if>

<%@include file="footer.jsp" %>
