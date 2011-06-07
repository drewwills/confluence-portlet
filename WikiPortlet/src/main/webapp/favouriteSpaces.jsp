<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work for
    additional information regarding copyright ownership.

    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file except in
    compliance with the License. You may obtain a copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
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
