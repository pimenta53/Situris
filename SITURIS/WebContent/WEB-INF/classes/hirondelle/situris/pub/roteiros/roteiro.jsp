<%@ page
	import="hirondelle.situris.pub.pontosReferencias.PontoReferencia"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>


<script
	src=" http://maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyDrBSpUZiez2WsUYed8vGyPgbxF9LyRHx0"
	type="text/javascript">
</script>
<script type="text/javascript">

	var map;
	var i=1;
	var markers = [];
	var markersR = [];
	var markerBounds;

	var paddings = {top:30, right:10, left:50};

	function load() {
		if (GBrowserIsCompatible()) {
			map = new GMap2(document.getElementById("map"));
			map.addControl(new GOverviewMapControl());
			map.enableDoubleClickZoom();
			map.enableScrollWheelZoom();
			map.addControl(new GMapTypeControl());
			map.addControl(new GSmallMapControl());
			geocoder = new GClientGeocoder();
			map.setCenter(new GLatLng(60.17 ,24.94), 8);

<%try {

				String connectionURL = "jdbc:mysql://localhost:3306/situris";
				Connection connection = null;
				Statement statement = null;
				ResultSet rs = null;
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(connectionURL, "root",	"root");
				statement = connection.createStatement();


				//lista de eventos
				ResultSet rs1 = null;
				String QueryString = "SELECT e.idEvento, e.nome, gps.latitude, gps.longitude, ti.descricao, e.dataInicio, e.dataFim, rpr.posicao FROM(((((Roteiro r LEFT JOIN Roteiro_PontoReferencia rpr ON r.idRoteiro = rpr.idRoteiro)LEFT JOIN PontoReferencia pr ON rpr.idPontoRef = pr.idPontoRef) LEFT JOIN PontoReferencia_Evento pre ON pr.idPontoRef = pre.idPontoRef) LEFT JOIN Evento e ON e.idEvento = pre.idEvento)	LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = e.idTipoInteresse) LEFT JOIN CoordenadasGPS gps ON gps.idCoordenadasGPS = e.idCoordenadasGPS	WHERE r.idRoteiro = "+ request.getParameter("ParentId") +" AND pr.privadoPontoRef=0 AND r.privadoRoteiro=0 GROUP BY e.idEvento";
				rs1 = statement.executeQuery(QueryString);
				while(rs1.next()){%>
					if(<%=rs1.getString(3)%>!=null){
					   var lat = parseFloat(<%=rs1.getString(3)%>);
	                   var lng = parseFloat(<%=rs1.getString(4)%>);
	                   var pos = new GLatLng(lat,lng);
	                   var linkE = "http://localhost:8080/situris/pub/centrosInteresse/EventoAction.show?ParentId="+<%=rs1.getString(1)%>;
	                   var html = "<b>Event - </b><b>" + "<%=rs1.getString(2)%>" + "</b><br/>" + "<%=rs1.getString(5)%>" + "<br/>" + "<%=rs1.getString(6)%>" + " - " + "<%=rs1.getString(7)%>" +"<br/><br/><a href=" + linkE + "> details </a>";
				       var marker = createMarker(pos, html, "evento", parseInt(<%=rs1.getString(8)%>));
				       map.addOverlay(marker);
				       markers.push(marker);
			 		}
		     	<%}
				rs1.close();

				//lista de Visitas
				ResultSet rs2 = null;
				QueryString = "SELECT v.idVisita, v.nome, gps.latitude, gps.longitude, ti.descricao, rpr.posicao FROM (((((Roteiro r LEFT JOIN Roteiro_PontoReferencia rpr ON r.idRoteiro = rpr.idRoteiro) LEFT JOIN PontoReferencia pr ON rpr.idPontoRef = pr.idPontoRef) LEFT JOIN PontoReferencia_Visita prv ON pr.idPontoRef = prv.idPontoRef) LEFT JOIN Visita v ON v.idVisita = prv.idVisita) LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = v.idTipoInteresse) LEFT JOIN CoordenadasGPS gps ON gps.idCoordenadasGPS = v.idCoordenadasGPS WHERE r.idRoteiro = "+ request.getParameter("ParentId") +" AND pr.privadoPontoRef=0 AND r.privadoRoteiro=0 GROUP BY v.idVisita";
				rs2 = statement.executeQuery(QueryString);
				while(rs2.next()){%>
					if(<%=rs2.getString(3)%>!=null){
					   var lat = parseFloat(<%=rs2.getString(3)%>);
	                   var lng = parseFloat(<%=rs2.getString(4)%>);
	                   var pos = new GLatLng(lat,lng);
	                   var linkV = "http://localhost:8080/situris/pub/centrosInteresse/VisitaAction.show?ParentId="+<%=rs2.getString(1)%>;
	                   var html = "<b>Visit - </b><b>" + "<%=rs2.getString(2)%>" + "</b><br/>" + "<%=rs2.getString(5)%>" + "<br/><br/><a href=" + linkV + "> details </a>";
				       var marker = createMarker(pos, html, "visita", parseInt(<%=rs2.getString(6)%>));
				       map.addOverlay(marker);
				       markers.push(marker);
			 		}
		     	<%}
				rs2.close();

				//lista de pontos

				QueryString = "SELECT pr.idPontoRef, pr.nome, pr.descricao, pr.privadoPontoRef, pr.area, gps.latitude, gps.longitude, gps.altitude, p.nomePais, rpr.posicao FROM (((Roteiro r LEFT JOIN Roteiro_PontoReferencia rpr ON r.idRoteiro = rpr.idRoteiro) LEFT JOIN PontoReferencia pr ON rpr.idPontoRef = pr.idPontoRef) LEFT JOIN pais p ON pr.idPais = p.idPais) LEFT JOIN CoordenadasGPS gps ON pr.idCoordenadasGPS = gps.idCoordenadasGPS WHERE rpr.idRoteiro = "+ request.getParameter("ParentId") + " and r.privadoRoteiro = 0 GROUP BY pr.idPontoRef ORDER BY rpr.posicao ASC";
				rs = statement.executeQuery(QueryString);
				while (rs.next()) {%>
					if(<%=rs.getString(6)%>!=null){
					   var lat = parseFloat(<%=rs.getString(6)%>);
					   var lng = parseFloat(<%=rs.getString(7)%>);
	                   var pos = new GLatLng(lat,lng);
	                   var linkP = "http://localhost:8080/situris/pub/pontosReferencias/VerPontoAction.show?ParentId="+<%=rs.getString(1)%>;
	                   var html = "<b>Reference Point - </b><b>" + "<%=rs.getString(2)%>" +"</b><br/>"+ "<%=rs.getString(9)%>" +"<br/><br/><a href=" + linkP + "> details </a>";
				       var marker = createMarker(pos, html, "ponto", parseInt(<%=rs.getString(10)%>));
				       i++;
				       map.addOverlay(marker);
		               markers.push(marker);
		               markersR.push(marker);
				 	}
			     <%}
				rs.close();

				statement.close();
				connection.close();
			} catch (Exception ex) {
				System.out.println("ERRO: ");
				System.out.println(ex.toString());
}%>
			drawRoute();
			show();
		}
	}

	function drawRoute(){
		var m = [];

		for(var i=0; i<markersR.length; i++){
			m[i] = markersR[i].getLatLng();
		}

		var polyline = new GPolyline(m, "FF0000", 10);
	    map.addOverlay(polyline);
	}


	function myclick(i) {
		gmarkers[i].openInfoWindowHtml(htmls[i]);
	}

	 function show(){
		  markerBounds = new GLatLngBounds();
		  for(var i=0; i<markers.length; i++){
		    markerBounds.extend(markers[i].getLatLng());
		  }
		  map.showBounds(markerBounds, paddings);
	}

	function createMarker(point, html, tipo, posicao) {

		var image = null;
		if(tipo == "visita") image = "../../images/largeTDGreenIcons/marker"+ posicao +".png";
		if(tipo == "evento") image = "../../images/largeTDBlueIcons/marker"+ posicao +".png";
		if(tipo == "ponto") image = "../../images/largeTDRedIcons/marker"+ posicao +".png";

		var icon = new GIcon(G_DEFAULT_ICON);
		icon.image = image;
		var marker = new GMarker(point, {
			icon : icon
		});
		GEvent.addListener(marker, "click", function() {
			marker.openInfoWindowHtml(html);
		});
		GEvent.addListener(marker, 'dragend', function(){
		      show();
		});
		return marker;
	}

	GMap2.prototype.showBounds = function(bounds_, opt_options){
		  var opts = opt_options||{};
		  opts.top = opt_options.top*1||0;
		  opts.left = opt_options.left*1||0;
		  opts.bottom = opt_options.bottom*1||0;
		  opts.right = opt_options.right*1||0;
		  opts.save = opt_options.save||true;
		  opts.disableSetCenter = opt_options.disableSetCenter||false;
		  var ty = this.getCurrentMapType();
		  var port = this.getSize();
		  if(!opts.disableSetCenter){
		    var virtualPort = new GSize(port.width - opts.left - opts.right,
		                            port.height - opts.top - opts.bottom);
		    this.setZoom(ty.getBoundsZoomLevel(bounds_, virtualPort));
		    var xOffs = (opts.left - opts.right)/2;
		    var yOffs = (opts.top - opts.bottom)/2;
		    var bPxCenter = this.fromLatLngToDivPixel(bounds_.getCenter());
		    var newCenter = this.fromDivPixelToLatLng(new GPoint(bPxCenter.x-xOffs, bPxCenter.y-yOffs));
		    this.setCenter(newCenter);
		  }
		}
</script>

</head>


<body
	onload="load()"
	onunload="GUnload()">


	<div class="container">
	<div id="output" align="center" >
		<div class="extern">



		<!--!	<input type="button" value="Get Directions"
				onclick="getDirections();" />-->
		    <div id="nomeRoteiro">
		  
		    
				<c:if test="${not empty itemForEdit.nome}">
				 ${itemForEdit.nome}
			    </c:if>
		
			
				
			<c:if test="${not empty itemForEdit.estrelas}">
					<c:set var="estrelasRot" value="${itemForEdit.estrelas}" />
					<%
						for (int j = 1; j <= (Integer) pageContext
									.getAttribute("estrelasRot"); j++) {
					%>
					<img class="no-margin" src="../../images/star.png" title="star"
						width="19px">
					<%
						}
					%>	

			</c:if> </div> 
		
			<div id="tipoRoteiro">
			<c:if test="${not empty itemForEdit.descricaoTI}">
				 ${itemForEdit.descricaoTI}
			</c:if></div>
	
		   </div>

			</div>
			</div>
	<div align="center" id="map" style="width: 900px; height:320px"></div>


     <div id="textoRoteiro">	<c:if test="${not empty itemForEdit.descricao}">

               ${itemForEdit.descricao}
			</c:if></div>
	<div id="pormenores">vvjvj</div>
	
			<br/>
			<br/>

			
	<h3>Comments</h3>

	<div class="container">
		<c:forEach var="item2" items="${y}" varStatus="index">
			<div class="box5">

				<div class="g">By ${item2.nomeUtilizador} on ${item2.data}</div>
				<p>
					<c:if test="${not empty item2.estrelas}">
						<c:set var="estrelasRot" value="${item2.estrelas}" />
              Evaluation:
                <%
							for (int j = 1; j <= (Integer) pageContext
											.getAttribute("estrelasRot"); j++) {
						%>
						<img class="no-margin" src="../../images/star.png" title="star"
							width="14px;">
						<%
							}
						%>
					</c:if>
				</p>
				<c:if test="${not empty item2.comentario}">
					<p>Comment: ${item2.comentario}</p>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<br />
	<div class="comments">
		<p class=g>Let here your evaluation:</p>
		<form action="/situris/main/evaluation/EvaluationRoteiroAction.apply"
			method="POST" class="user-input" name='giveMeFocus'>
			<table>
				<tr>
					<td><label>Evaluation</label></td>
					<br/>
					<td><input type="radio" name="stars" value="1" /><label
						for="star1" title="Schlecht"> 1</label> <input type="radio"
						name="stars" value="2" /><label for="star2" title="So gut wie">
							2</label> <input type="radio" name="stars" value="3" /><label
						for="star3" title="Gut"> 3</label> <input type="radio"
						name="stars" value="4" /><label for="star4" title="Geil">
							4</label> <input type="radio" name="stars" value="5" /><label
						for="star5" title="Super !!"> 5</label></td>
				</tr>
				<tr>
					<td>Comment:</td>
					<td><textarea name=comment cols="40" rows="5"></textarea></td>
				</tr>
				<tr>
					<td><input type="hidden" name=idRoteiro
						value='${itemForEdit.idRoteiro}'></td>
				</tr>
				<tr>
					<td align="center" colspan=2><input type=submit value="Ok">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br/>
</body>
