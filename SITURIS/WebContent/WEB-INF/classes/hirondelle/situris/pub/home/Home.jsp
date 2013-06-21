	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

	<meta http-equiv="Content-Language" content="en-us" />

	
	<!-- Begin JavaScript -->
		  <script type="text/javascript" src="../../jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="../../jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="../../jquery.coda-slider-2.0.js"></script>
		<script type="text/javascript">
		$().ready(function() {
			
			$('#coda-slider-1').codaSlider({
			
			autoSlide: true,
			
			autoSlideInterval: 8000,
			
			autoSlideStopWhenClicked: true
			
			});
		
			});
		 </script>
	<!-- End JavaScript -->


<body class="coda-slider-no-js">
<div id="titulo">
<h1> The best routes in the moment</h1></div>
  <c:if test="${not empty Roteiros}">

    <ol>

<div class="coda-slider-wrapper">
	<div class="coda-slider preload" id="coda-slider-1">
	
 <c:forEach var='rot' items='${Roteiros}'>
 <c:url value="/pub/roteiros/RoteiroAction.show" var="rotURL">
          <c:param name='ParentId' value='${rot.idRoteiro}' />
        </c:url>
	<div class="panel">
			<div class="panel-wrapper">
						
		
	</div>
	<div class="left">

			<div id="texto">
			
					
			<div id="tituloHome">	 <a href='${rotURL}'>${rot.nome}</a> </div></div>
			
				<div id="TipoInteresse">${rot.descricaoTI}</div>	

				
				
				<c:if test="${not empty rot.estrelas}">
				
				<div id="stars">
				<c:set var="estrelasRoteiro" value="${rot.estrelas}" />
				
				<%for (int i = 1; i <= (Integer) pageContext.getAttribute("estrelasRoteiro"); i++) {%>
				  <img class="no-margin"  src="../../images/star.png" title="star" width="20px;">
				<%}%>
				</div>
			</c:if>
			
			</div>
		<div class="right">
		<div >
		 <img   id="imagemRoteiro"src="../../images/pizzas.jpeg">
		</div>	
		
		</div>
		

      </c:forEach>
			
			
			
		</div>
	</div><!-- .coda-slider -->
</div>
	      
   
    </ol>
  </c:if>
	
	
	
</div><!-- .coda-slider-wrapper -->
<!--
<div class="patrocinios10">
  <c:if test="${not empty Patrocinios}">
  <h2>10 most popular events/visits:</h2>
  <ol>
  
    <c:forEach var='pat' items='${Patrocinios}'>
      <c:if test="${ not empty pat.idEvento}">
        <c:url value="/pub/centrosInteresse/EventoAction.show" var="patURL">
          <c:param name='ParentId' value='${pat.idEvento}' />
        </c:url> 
        <li><a href='${patURL}'>${pat.nomeEvento}</a>
      </c:if>
      <c:if test="${not empty pat.idVisita}">
        <c:url value="/pub/centrosInteresse/VisitaAction.show" var="patURL">
          <c:param name='ParentId' value='${pat.idVisita}' />
        </c:url> 
        <li><a href='${patURL}'>${pat.nomeVisita}</a>
      </c:if>
      
    </c:forEach>
  </ol>
  </c:if>
</div>-->
</html>