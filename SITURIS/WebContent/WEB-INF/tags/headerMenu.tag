<%@ include file="/WEB-INF/TagHeader.jspf" %>
<br/><br/>

<div id="menuhorizontal" align="center">

  <c:url var='homePageURL' value='/pub/home/HomeAction.list' />
  <a class="g" href='${homePageURL}' title='Home page'>Home</a></li>
       <a class=w href='' title='espaco'>---- </a>  
  <c:url var='locaisVisitarURL' value='/Locals.jsp' /> 
  <a class="g" href='${locaisVisitarURL}' title='Places to Visit'>Places to visit</a>  
       <a class=w href='' title='espaco'>---- </a>    
  <c:url var='roteirosURL' value='/pub/roteiros/RoteirosAction.list' />
  <a class="g" href='${roteirosURL}' title='Rotes'>Routes</a> 
       <a class=w href='' title='espaco'>---- </a>     
  <c:url var='reportURL' value='/pub/pontosReferencias/ListaPontoAction.list' />
  <a class="g" href='${reportURL}' title='Reference Points'>Reference Points</a></li>
       <a class=w href='' title='espaco'>---- </a> 
  <c:url var='reportURL' value='/pub/centrosInteresse/VisitasAction.list' />
  <a class="g" href='${reportURL}' title='Visits'>Visits</a></li>
        <a class=w href='' title='espaco'>---- </a> 
  <c:url var='eventosURL' value='/pub/centrosInteresse/EventosAction.list' />
  <a class="g" href='${eventosURL}' title='Events'>Events</a>
</div>

<br/><br/>
