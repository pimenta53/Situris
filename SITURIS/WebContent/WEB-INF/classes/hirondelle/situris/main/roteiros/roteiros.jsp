<div class="container">
</div>
  <div align="right">
  <c:url var='roteiroURL' value='/main/roteiros/NewRoteiroAction.show' />
  <a href='${roteiroURL}' title='Routes'>Add a new Route</a>
  </div>
<c:if test="${not empty itemsForListing}">
<table class="admintables" title="All Routes Available" align="center"> 
 <tr>
  <th title="Line Number"></th>
  <th>Title</th>
  <th>Interest Type</th>
  <th>Number of Evaluations</th>
  <th>Average Rating</th>
 </tr>
<w:alternatingRow> 
<c:forEach var="item" items="${itemsForListing}" varStatus="index">
 <tr class="row_highlight">
  <td title="Line Number">${index.count}</td>
  <td>
    <c:if test="${not empty item.idRoteiro}">
      <c:url value="/pub/roteiros/RoteiroAction.show" var="routeURL">
        <c:param name='ParentId' value='${item.idRoteiro}' />
      </c:url> 
     <a href='${routeURL}'>${item.nome}</a>
   </c:if>
  </td>
  <c:if test="${not empty item.descricaoTI}"><td>${item.descricaoTI}</td></c:if>
  <c:if test="${not empty item.numAva}"><td align='center'>${item.numAva}</td></c:if>
  <td align='center'>
  <c:if test="${not empty item.estrelasMedia}">${item.estrelasMedia}</c:if>
  <c:if test="${empty item.estrelasMedia}"> - </c:if>
  </td>
  <td align="center">
    <c:url value='GestaoRoteirosAction.fetchForChange' var='showURL'/>
    <form action='${showURL}' method='POST'>
      <input type="hidden" value='${item.idRoteiro}' name="IdRoteiro">
      <input type="submit" value='Edit'>
    </form> 
  </td> 
  <td align="center">
    <c:url value='DeleteRouteAction.apply' var='delURL'>
    <c:param name='IdRoteiro' value='${item.idRoteiro}' />
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