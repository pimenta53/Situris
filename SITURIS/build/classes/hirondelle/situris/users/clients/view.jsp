<%-- Listing of all Prediction lists. --%>
<c:if test="${not empty itemsForListing}">
<table class="admintables" title="Proposals Made" align="center"> 
 <tr>
  <th title="Line Number"></th>
  <th>Description</th>
  <th>Value</th>
  <th>Date</th>
  <th>Status</th>
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
    <c:if test="${item.estado == 0}">Under Review...</c:if>
    <c:if test="${item.estado == 1}">Accepted</c:if>
  </td>
 </tr>
</c:forEach>
</w:alternatingRow>
</table>
</c:if>

