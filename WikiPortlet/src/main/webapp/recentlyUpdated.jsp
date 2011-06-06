<%@include file="header.jsp" %>

<div class="portlet-tabs-wrapper portlet-container-flex">
    <ul class="portlet-tabs portlet-tabs-left">
        <li class="portlet-tabs-active">
                Recently Updated
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
        <li>
            <a href='<portlet:actionURL><portlet:param name="Action" value="Search" /></portlet:actionURL>'>
                Search
            </a>
        </li>
    </ul>
</div>

<c:choose>
  <c:when test="${empty Listings}">
      <p  class="portlet-msg-info portlet-container-flex"><strong>${brandingMessages["noRecentlyUpdated"]}</strong></p>
  </c:when>
  <c:otherwise>
    <%@include file="listing.jsp" %>
  </c:otherwise>
</c:choose>


<%@include file="footer.jsp" %>
