<%-- Listing of all Prediction lists. --%>
<c:if test="${not empty itemsForListing}">

<table class="admintables" title="Proposals Made" align="center"> 
 <tr>
  <th title="Line Number">#</th>
  <th>Description</th>
  <th>Value</th>
  <th>Date</th>
  <th>State</th>
  <th>User/Client</th>
 </tr>
<w:alternatingRow> 
<c:forEach var="item" items="${itemsForListing}" varStatus="index">
 <tr class="row_highlight">
  <td title="Line Number">${index.count}</td>
  <td>${item.descricao}</td>
  <td>${item.valor} &euro;</td>
  <td>
   <c:set value="${item.date}" var="date"/>
   <w:showDateTime name="date" pattern="MMMM DD, YYYY"/>
   </td>
  <td>
    <c:if test="${item.estado == 0}">Not accepted</c:if>
    <c:if test="${item.estado == 1}">Accepted</c:if>
  </td>
  <td>
    <c:if test="${not empty item.idUser}">
      <c:url value="/users/showaccount/ShowAccountAction.show" var="userURL">
        <c:param name='ParentId' value='${item.idUser}' />
      </c:url> 
     <a href='${userURL}'>Details</a>
   </c:if>
  </td>
  <td>
  <c:if test="${item.estado == 0}">
  <td align="center">
    <c:url value='NewPatrocinioAction.show' var='showURL' >
      <c:param name='Id' value='${item.id}' />
      <c:param name='ParentId' value='${item.idUser}' />
    </c:url>
    <form action='${showURL}' method='POST'>
      <input type="submit" value='Accept'>
    </form> 
  </td> 
  </c:if>
  </td>
 </tr>
</c:forEach>
</w:alternatingRow>
</table>
</c:if>

