<%@include file="header.jsp" %>

<div class="portlet-tabs-wrapper">
    <ul class="portlet-tabs portlet-tabs-left">  
    <li>
      <a href='<portlet:actionURL><portlet:param name="Action" value="RecentlyUpdated" /></portlet:actionURL>'>
        Recently Updated
      </a>
    </li>
        <li class="portlet-tabs-active">
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
  
</div>

<c:choose>
  <c:when test="${empty Listings}">
      <p class="portlet-msg-info  portlet-container-flex"><strong>${brandingMessages["noFavouriteSpaces"]}</strong></p>
  </c:when>
  <c:otherwise>
    <%@include file="listing.jsp" %>
  </c:otherwise>
</c:choose>


<%@include file="footer.jsp" %>
