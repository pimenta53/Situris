<%-- Search all predictions for given text. --%>
<c:url value="SearchAction.search" var="searchURL"/> 
<w:populate> 
<form action='${searchURL}' method="GET" class="user-input"  name='giveMeFocus'>
 <table align="center">
  <tr>
   <td> <label>Search For</label> </td>
   <td><input name="Search Text" type="text" size='68' title='3..255 characters'></td>
  </tr>
</w:populate> 
  <tr>
   <td align="center" colspan=2>
      <input type=submit value="Search">
   </td>
  </tr>
 </table>
</form>

<p>

<c:if test="${not empty mRots}">
  <h3>Routes</h3>
  <c:forEach var='rot' items='${mRots}'>

    <c:if test="${rot.privado == 0}">
      <c:url value="/pub/roteiros/RoteiroAction.show" var="rotURL">
        <c:param name='ParentId' value='${rot.idPoI}' />
      </c:url>   
    </c:if>
    <c:if test="${rot.privado != 0}">
      <c:url value="/main/roteiros/RoteiroAction_PRM.show" var="rotURL">
        <c:param name='ParentId' value='${rot.idPoI}' />
      </c:url>   
    </c:if>
  
    <b>Name:</b> <a href='${rotURL}'>${rot.nome}</a>
  </c:forEach>
<p/>
</c:if>

<c:if test="${not empty mRefs}">
  <h3>Reference Points</h3>
  <c:forEach var='ptRef' items='${mRefs}'>

    <c:if test="${ptRef.privado == 0}">
     <c:url value="/pub/pontosReferencias/VerPontoAction.show" var="ptRefURL">
       <c:param name='ParentId' value='${ptRef.idPoI}' />
     </c:url>   
    </c:if>
    <c:if test="${ptRef.privado != 0}">
     <c:url value="/main/pontosReferencia/PontoAction_PRM.list" var="ptRefURL">
       <c:param name='ParentId' value='${ptRef.idPoI}' />
     </c:url>   
    </c:if>
    
    <b>Name:</b> <a href='${ptRefURL}'>${ptRef.nome}</a>
  </c:forEach>
<p/>
</c:if>

<c:if test="${not empty mVis}">
  <h3>Visits</h3>
  <c:forEach var='vis' items='${mVis}'>
 
    <c:if test="${vis.privado == 0}">
      <c:url value="/pub/centrosInteresse/VisitaAction.show" var="visURL">
        <c:param name='ParentId' value='${vis.idPoI}' />
      </c:url> 
    </c:if>
    <c:if test="${vis.privado != 0}">
      <c:url value="/main/centrosInteresse/VisitaAction_PRM.show" var="visURL">
        <c:param name='ParentId' value='${vis.idPoI}' />
      </c:url> 
    </c:if>
  
    <b>Name:</b> <a href='${visURL}'>${vis.nome}</a>
  </c:forEach>
<p/>
</c:if>

<c:if test="${not empty mEvs}">
  <h3>Events</h3>
  <c:forEach var='ev' items='${mEvs}'>
  
    <c:if test="${ev.privado == 0}">
      <c:url value="/pub/centrosInteresse/EventoAction.show" var="evURL">
        <c:param name='ParentId' value='${ev.idPoI}' />
      </c:url> 
    </c:if>
    <c:if test="${ev.privado != 0}">
      <c:url value="/main/centrosInteresse/EventoAction_PRM.show" var="evURL">
        <c:param name='ParentId' value='${ev.idPoI}' />
      </c:url> 
    </c:if>
  
    <b>Name:</b> <a href='${evURL}'>${ev.nome}</a>
  </c:forEach>
<p/>
</c:if>

