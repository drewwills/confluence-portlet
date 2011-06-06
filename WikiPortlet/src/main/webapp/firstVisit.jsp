<%@include file="header.jsp" %>

<div id="wikiportlet-nav">
  <ul>
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
    <li>
      <a href='<portlet:actionURL><portlet:param name="Action" value="Search" /></portlet:actionURL>'>
        Search
      </a>
    </li>
  </ul>
  <div class="wikiportlet-clear"></div>
</div>
<div class="wikiportlet-clear"></div>

<div class="portlet-msg-info">${brandingMessages["view-firstVisit"]}</div>


<%@include file="footer.jsp" %>
