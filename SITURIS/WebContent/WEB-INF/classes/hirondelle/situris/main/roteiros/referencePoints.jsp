<c:if test="${not empty PtRefs}">
<table title="All Reference Points Available" align="center"> 
 <tr>
  <th title="Line Number">#</th>
  <th>Title</th>
  <th>Country</th>
  <th>Position</th>
 </tr>
<w:alternatingRow> 
<c:forEach var="item" items="${PtRefs}" varStatus="index">
 <c:set var="exist" value="false"/>
  <c:forEach var="ids" items="${ids}">
    <c:if test="${item.idPontoRef == ids.idPr}">
      <c:set var="exist" value="true"/>
      <c:set var="position" value="${ids.pos}"/>
    </c:if>
  </c:forEach>
  <tr class="row_highlight">
  <td title="Line Number">${index.count}</td>
  <td>
    <c:if test="${not empty item.idPontoRef}">
      <c:url value="/pub/pontosReferencias/VerPontoAction.show" var="ptrefURL">
        <c:param name='ParentId' value='${item.idPontoRef}' />
      </c:url> 
     <a href='${ptrefURL}'>${item.nome}</a>
   </c:if>
  </td>
  <td align='center'><c:if test="${not empty item.nomePais}">${item.nomePais}</c:if></td>
  <c:if test="${!exist}">
  <c:url value='AddRemoveAction.add' var='addURL'/>
  <form action='${addURL}' method='POST'>
  <td align='center'>
    <select name="posicao">
    <option> </option>
      <c:forEach var="pos" items="${pos}" varStatus="index">
        <option value="${pos}">${pos}</option>
      </c:forEach>
    </select>
  </td>
    <td align="center">
        <input type="hidden" value='${item.idPontoRef}' name="IdPtRef">
        <input type="submit" value='Add'>
      </form> 
    </td> 
  </c:if>
  <c:if test="${exist}">
  <td align="center"> ${position} </td>
  <td align="center">
    <c:url value='AddRemoveAction.delete' var='delURL'>
    <c:param name='IdPtRef' value='${item.idPontoRef}' />
   </c:url>
    <form action='${delURL}' method='POST'>
      <input type="hidden" value="${position}" name="posicao">
      <input type="submit" value='Remove'>
    </form> 
  </td> 
  </c:if>
 </tr>
</c:forEach>
</w:alternatingRow>
</table>
</c:if>