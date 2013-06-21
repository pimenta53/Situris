<%-- Listing of all Prediction lists. --%>
<div>
<c:if test="${not empty dados}">

<h2>Payment</h2>
<c:if test="${not empty dados.nomeEvento}"><p>Event: ${dados.nomeEvento}</p></c:if>
<c:if test="${not empty dados.nomeVisita}"><p>Visit: ${dados.nomeVisita}</p></c:if>
<p>Sponsorship description: ${dados.descricaoPat}</p>
<p>Contract from ${dados.dataInicio} to ${dados.dataFim}</p>
<p>Profile: ${dados.descricaoPer}</p>
<p>Category: ${dados.descricaoCat}</p>
<p>Number of clicks: ${dados.NCliques}</p>
<p>Value paid: ${value} &euro;</p>
<p>Client: ${dados.nomeUser}</p>
<p>Do you confirm the payment?</p>
<c:url value='NewPagamentoAction.apply' var='pagURL' />
<form action='${pagURL}' method='POST'>
  <input type="hidden" value='${dados.idTabelaPrecos}' name="idTabela">
  <input type="hidden" value='${pat}' name="Id">
  <input type="hidden" value='${value}' name="value">
  <input type="submit" value='Confirm'>
</form> 
</c:if>
<c:if test="${empty dados}">
It wasn't possible to retrieve the necessary information!
</c:if>
</div>