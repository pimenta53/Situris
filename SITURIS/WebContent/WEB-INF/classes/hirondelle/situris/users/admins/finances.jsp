<c:if test="${not empty pays}">
<table class="admintables" title="Finances" align="center">
 <tr>
  <th title="Line Number">#</th>
  <th align="center">Name</th>
  <th align="center">Client</th>
  <th align="center">Category</th>
  <th align="center">Profile</th>
  <th align="center">Payment Date</th>
  <th align="center">Value</th>
 </tr>
<w:alternatingRow>
<c:forEach var="item" items="${pays}" varStatus="index">
 <tr class="row_highlight">
  <td title="Line Number">${index.count}</td>
  <td>
    <c:if test="${not empty item.nomeVisita}">${item.nomeVisita}</c:if>
    <c:if test="${not empty item.nomeEvento}">${item.nomeEvento}</c:if>
    <c:if test="${not empty item.nomeUser}">${item.nomeUser}</c:if>
  </td>
  <td>
    <c:if test="${not empty item.nomeUser}">${item.nomeUser}</c:if>
  </td>
  <td>
    <c:if test="${not empty item.descricaoCat}">${item.descricaoCat}</c:if>
  </td>
  <td>
    <c:if test="${not empty item.descricaoPer}">${item.descricaoPer}</c:if>
  </td>
  <td align="center">
   <c:set value="${item.dataPag}" var="date"/>
   <w:showDateTime name="date" pattern="MMMM DD, YYYY"/>
  </td>
  <td>
    <c:if test="${not empty item.valor}">${item.valor} &euro;</c:if>
  </td>
 </tr>
</c:forEach>
</w:alternatingRow>
</table>
</c:if>
<c:if test="${empty pays}">
  No payment was made yet!
</c:if>
