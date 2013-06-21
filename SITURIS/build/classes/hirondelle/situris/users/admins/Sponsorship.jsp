<%-- Listing of all Prediction lists. --%>
<c:if test="${not empty itemsForListing}">
<table class="admintables" title="Sponsorships" align="center">
 <tr>
  <th title="Line Number">#</th>
  <th>Description</th>
  <th>Begin Date</th>
  <th>End Date</th>
  <th>Clicks</th>
  <th>Payment State</th>
  <th>Link</th>
  <th>Client</th>
 </tr>
<w:alternatingRow>
<c:forEach var="item" items="${itemsForListing}" varStatus="index">
 <tr class="row_highlight">
  <td title="Line Number">${index.count}</td>
  <td>${item.descricao}</td>
  <td>
   <c:set value="${item.dateInicio}" var="date"/>
   <w:showDateTime name="date" pattern="MMMM DD, YYYY"/>
   </td>
  <td>
   <c:set value="${item.dateFim}" var="date"/>
   <w:showDateTime name="date" pattern="MMMM DD, YYYY"/>
  </td>
  <td align="center">${item.cliques}</td>
  <td align="center">
    <c:if test="${item.estadoPagamento == 0}">Not Paid</c:if>
    <c:if test="${item.estadoPagamento == 1}">Paid</c:if>
  </td>
  <td>
    <c:if test="${not empty item.idEvento}">
      <c:url value="/pub/centrosInteresse/EventoAction.show" var="evURL">
        <c:param name='ParentId' value='${item.idEvento}' />
      </c:url>
      <a href='${evURL}'>Event</a>
    </c:if>
    <c:if test="${not empty item.idVisita}">
      <c:url value="/pub/centrosInteresse/VisitaAction.show" var="visURL">
        <c:param name='ParentId' value='${item.idVisita}' />
      </c:url>
      <a href='${visURL}'>Visit</a>
    </c:if>
    <c:if test="${empty item.idEvento && empty item.idVisita}"> - </c:if>
  </td>
  <td>
    <c:if test="${not empty item.idUser}">
      <c:url value="/users/showaccount/ShowAccountAction.show" var="visURL">
        <c:param name='ParentId' value='${item.idUser}' />
      </c:url>
      <a href='${visURL}'>Details</a>
    </c:if>
  </td>
  <td>
  <c:if test="${item.estadoPagamento == 0}">
  <td align="center">
    <c:url value='NewPagamentoAction.show' var='showURL' >
      <c:param name='Id' value='${item.id}' />
    </c:url>
    <form action='${showURL}' method='POST'>
      <input type="submit" value='Pay'>
    </form> 
  </td> 
  </c:if>
  </td>
 </tr>
</c:forEach>
</w:alternatingRow>
</table>
</c:if>

