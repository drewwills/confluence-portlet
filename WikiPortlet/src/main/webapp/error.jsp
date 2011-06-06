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
        My Pages
      </a>
    </li>
    <li>
      <a href='<portlet:actionURL><portlet:param name="Action" value="Search" /></portlet:actionURL>'>
        Search
      </a>
    </li>
  </ul>
</div>

<p class="portlet-msg-error portlet-container-flex">
${Error}
</p>

<%@include file="footer.jsp" %>