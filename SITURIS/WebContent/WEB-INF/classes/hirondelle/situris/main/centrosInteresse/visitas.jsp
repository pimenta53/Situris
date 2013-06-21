<div class="container">
</div>
  <div align="right">
  <c:url var='visURL' value='/main/centrosInteresse/VisitaNewAction.show' />
  <a href='${visURL}' title='vis'>Add a new Visit</a>
  </div>
<c:if test="${not empty itemsForListing}">
<table class="admintables" title="All Visits Available" align="center"> 
 <tr>
  <th title="Line Number"></th>
  <th>Title</th>
  <th>Link</th>
  <th>Interest Type</th>
  <th>No. Evaluations</th>
  <th>Average Rating</th>
  <th>Sponsorship</th>
  <th>Sponsorship End Date</th>
 </tr>
<w:alternatingRow> 
<c:forEach var="item" items="${itemsForListing}" varStatus="index">
 <tr class="row_highlight">
  <td title="Line Number">${index.count}</td>
  <td>
    <c:if test="${not empty item.idVisita}">
      <c:url value="/pub/centrosInteresse/VisitaAction.show" var="visURL">
        <c:param name='ParentId' value='${item.idVisita}' />
      </c:url> 
     <a href='${visURL}'>${item.nome}</a>
   </c:if>
  </td>
  <td align='center'>
  <c:if test="${not empty item.link}"><a class=g href='${item.link}'><u>Website</u></a></c:if>
  <c:if test="${empty item.link}"> - </c:if>
  </td>
  <td align='center'><c:if test="${not empty item.descricaoTI}">${item.descricaoTI}</c:if></td>
  <td align='center'><c:if test="${not empty item.numAva}">${item.numAva}</c:if></td>
  <td align='center'>
    <c:if test="${not empty item.estrelasMedia}">${item.estrelasMedia}</c:if>
    <c:if test="${empty item.estrelasMedia}"> - </c:if>
  </td>
  <td align='center'>
  <c:if test="${empty item.pagamentoPat}">Doesn't Have</c:if>
  <c:if test="${item.pagamentoPat == 0}">Not Paid</c:if>
  <c:if test="${item.pagamentoPat == 1}">Paid</c:if>
  </td>
  <td align='center'>
    <c:if test="${not empty item.dataFimPat}">${item.dataFimPat}</c:if>
    <c:if test="${empty item.dataFimPat}"> - </c:if>
  </td>
  <td align="center">
    <c:url value='VisitaAction.fetchForChange' var='editURL'/>
    <form action='${editURL}' method='POST'>
      <input type="hidden" value='${item.idVisita}' name="IdVisita">
      <input type="submit" value='Edit'>
    </form> 
  </td> 
  <td align="center">
    <c:url value='VisitaAction.delete' var='delURL'>
    <c:param name='IdVisita' value='${item.idVisita}' />
   </c:url>
    <form action='${delURL}' method='POST'>
      <input type="submit" value='Delete'>
    </form> 
  </td> 
 </tr>
</c:forEach>
</w:alternatingRow>
</table>
</c:if>