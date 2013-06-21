<div class="container">
</div>
  <div align="right">
  <c:url var='ptRefURL' value='/main/pontosReferencia/NewPtRefAction.show' />
  <a href='${ptRefURL}' title='PtRefs'>Add a new Reference Point</a>
  </div>
<c:if test="${not empty itemsForListing}">
<table class="admintables" title="All Reference Points Available" align="center"> 
 <tr>
  <th title="Line Number"></th>
  <th>Title</th>
  <th>Country</th>
  <th>No. of Routes</th>
  <th>No. of Events</th>
  <th>No. of Visits</th>
  <th>No. of Evaluations</th>
  <th>Average Rating</th>
 </tr>
<w:alternatingRow> 
<c:forEach var="item" items="${itemsForListing}" varStatus="index">
 <tr class="row_highlight">
  <td title="Line Number">${index.count}</td>
  <td>
    <c:if test="${not empty item.idPontoRef}">
      <c:url value="/pub/pontosReferencias/VerPontoAction.show" var="ptRefURL">
        <c:param name='ParentId' value='${item.idPontoRef}' />
      </c:url> 
     <a href='${ptRefURL}'>${item.nome}</a>
   </c:if>
  </td>
  <td><c:if test="${not empty item.nomePais}">${item.nomePais}</c:if></td>
  <td align='center'><c:if test="${not empty item.numRoteiros}">${item.numRoteiros}</c:if></td>
  <td align='center'><c:if test="${not empty item.numEventos}">${item.numEventos}</c:if></td>
  <td align='center'><c:if test="${not empty item.numVisitas}">${item.numVisitas}</c:if></td>
  <td align='center'><c:if test="${not empty item.numAva}">${item.numAva}</c:if></td>
  <td align='center'>
  <c:if test="${not empty item.estrelasMedia}">${item.estrelasMedia}</c:if>
  <c:if test="${empty item.estrelasMedia}"> - </c:if>
  </td>
  <td align="center">
    <c:url value='GestaoPtRefAction.fetchForChange' var='showURL'/>
    <form action='${showURL}' method='POST'>
      <input type="hidden" value='${item.idPontoRef}' name="IdPtRef">
      <input type="submit" value='Edit'>
    </form> 
  </td> 
  <td align="center">
    <c:url value='GestaoPtRefAction.delete' var='delURL'>
    <c:param name='IdPtRef' value='${item.idPontoRef}' />
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

