<div class="portlet-table-wrapper">
   <table class="portlet-container-flex portlet-simple-table" border="0" cellspacing="0" cellpadding="0" >
    <tbody>
      <tr>
        <th>Wiki Page</th>
        <th>User Name</th>
        <th>Last Modified</th>
      </tr>
      <c:forEach var="listing" items="${Listings}">
        <tr>
          <td class="portlet-table-first-col portlet-table-col">
            <a target="_new" href='
              <portlet:actionURL>
                <portlet:param name="Action" value="Login" />
                <portlet:param name="Location" value="${listing.urlPath}" />
              </portlet:actionURL>'>
              ${listing.name}
            </a>
          </td>
          <td>${listing.lastModifier}</td>
          <td>${listing.lastModificationDateString}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
