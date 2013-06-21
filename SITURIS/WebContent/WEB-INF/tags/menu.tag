   <%@ include file="/WEB-INF/TagHeader.jspf" %>
    
 <w:show ifRole="admin">

  	<c:url var='roteiroPURL' value='/main/roteiros/RoteirosAction.list' />
    <a href='${roteiroPURL}' title='My Routes'>Manage Routes</a>
      <a class=w href='' title='espaco'>---- </a>
    <c:url var='pontosURL' value='/main/pontosReferencia/PontosAction.list' />
    <a href='${pontosURL}' title='My Reference Points'>Manage Reference Points</a>
      <a class=w href='' title='espaco'>---- </a>
    <c:url var='visitasPURL' value='/main/centrosInteresse/VisitasAction.list' />
    <a href='${visitasPURL}' title='My Visits'>Manage Visit</a>
      <a class=w href='' title='espaco'>---- </a>
    <c:url var='eventosPURL' value='/main/centrosInteresse/EventosAction.list' />
    <a href='${eventosPURL}' title='My Events'>Manage Event</a>

  </w:show>
    
