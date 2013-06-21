<%@ page import="hirondelle.situris.pub.centrosInteresse.Evento"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>


<script
	src=" http://maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyDrBSpUZiez2WsUYed8vGyPgbxF9LyRHx0"
	type="text/javascript">

</script>

<script type="text/javascript">

	var map;

	var markers = [];
	var markerBounds;

	var paddings = {top:10, right:10, left:50};

	function load(latitude, longitude, nome, pais) {
		if (GBrowserIsCompatible()) {
			map = new GMap2(document.getElementById("map"));
			map.addControl(new GOverviewMapControl());
			map.enableDoubleClickZoom();
			map.enableScrollWheelZoom();
			map.addControl(new GMapTypeControl());
			map.addControl(new GSmallMapControl());
			center = new GLatLng(latitude, longitude);
			geocoder = new GClientGeocoder();
			map.setCenter(new GLatLng(60.17 ,24.94), 8);

			var html = "<b>Reference Point - </b><b>"+ nome +"</b><br/>"+ pais;
			var marker = createMarker(center, html, "ponto");
			marker.openInfoWindowHtml(html);
		    map.addOverlay(marker);
		    markers.push(marker);

<%try {

				String connectionURL = "jdbc:mysql://localhost:3306/situris";
				Connection connection = null;
				Statement statement = null;
				ResultSet rs = null;
				ResultSet rs1 = null;
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(connectionURL, "root","root");
				statement = connection.createStatement();

				String QueryString = "SELECT e.idEvento, e.nome, e.descricao, e.link, e.imagem, gps.latitude, gps.longitude, ti.descricao, e.idUser, e.dataInicio, e.dataFim FROM (((PontoReferencia pr LEFT JOIN PontoReferencia_Evento pre ON pr.idPontoRef = pre.idPontoRef) LEFT JOIN Evento e ON e.idEvento = pre.idEvento) LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = e.idTipoInteresse) LEFT JOIN CoordenadasGPS gps ON gps.idCoordenadasGPS = e.idCoordenadasGPS WHERE pr.idPontoRef = "+ request.getParameter("ParentId") + " and pr.privadoPontoRef = 0";
				rs = statement.executeQuery(QueryString);
				while(rs.next()){%>
					if(<%=rs.getString(6)%>!=null){
					   var lat = parseFloat(<%=rs.getString(6)%>);
	                   var lng = parseFloat(<%=rs.getString(7)%>);
	                   var pos = new GLatLng(lat,lng);
	                   var linkE = "http://localhost:8080/situris/pub/centrosInteresse/EventoAction.show?ParentId="+<%=rs.getString(1)%>;
	                   var html = "<b>Event - </b><br/><b>" + "<%=rs.getString(2)%>" + "</b><br/>" + "<%=rs.getString(8)%>" + "<br/>" + "<%=rs.getString(10)%>" + " - " + "<%=rs.getString(11)%>" +"<br/><br/><a href=" + linkE + "> details </a>";
				       var marker = createMarker(pos, html, "evento");
				       map.addOverlay(marker);
				       markers.push(marker);
				 	}
			     <%}
				rs.close();


				QueryString = "SELECT v.idVisita, v.nome, v.descricao, v.link, v.imagem, gps.latitude, gps.longitude, ti.descricao, v.idUser FROM (((PontoReferencia pr LEFT JOIN PontoReferencia_Visita prv ON pr.idPontoRef = prv.idPontoRef) LEFT JOIN Visita v ON v.idVisita = prv.idVisita) LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = v.idTipoInteresse) LEFT JOIN CoordenadasGPS gps ON gps.idCoordenadasGPS = v.idCoordenadasGPS WHERE pr.idPontoRef = "+ request.getParameter("ParentId") + " and pr.privadoPontoRef = 0";
				rs1 = statement.executeQuery(QueryString);
				while(rs1.next()){%>
				   if(<%=rs1.getString(6)%>!=null){
					   var lat = parseFloat(<%=rs1.getString(6)%>);
	                   var lng = parseFloat(<%=rs1.getString(7)%>);
	                   var pos = new GLatLng(lat,lng);
	                   var linkV = "http://localhost:8080/situris/pub/centrosInteresse/VisitaAction.show?ParentId="+<%=rs1.getString(1)%>;
	                   var html = "<b>Visit - </b><b>" + "<%=rs1.getString(2)%>" + "</b><br/>" + "<%=rs1.getString(8)%>" + "<br/><br/><a href=" + linkV + "> details </a>";
				       var marker = createMarker(pos, html, "visita");
				       map.addOverlay(marker);
				       markers.push(marker);
			       }
                <%}
				rs1.close();

				statement.close();
				connection.close();
			} catch (Exception ex) {
				System.out.println("ERRO: ");
				System.out.println(ex.toString());
}%>
	show();
		}
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

	function createMarker(point, html, tipo) {
		var marker = new GMarker(point);

		var image = null;
		if(tipo == "ponto") image = "../../images/largeTDRedIcons/blank.png";
		if(tipo == "visita") image = "../../images/largeTDGreenIcons/blank.png";
		if(tipo == "evento") image = "../../images/largeTDBlueIcons/blank.png";

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
		    this.setZoom(ty.getBoundsZoomLevel(bounds_, virtualPort)-2);
		    var xOffs = (opts.left - opts.right)/2;
		    var yOffs = (opts.top - opts.bottom)/2;
		    var bPxCenter = this.fromLatLngToDivPixel(bounds_.getCenter());
		    var newCenter = this.fromDivPixelToLatLng(new GPoint(bPxCenter.x-xOffs, bPxCenter.y-yOffs));
		    map.setCenter(newCenter);
		  }
		}

</script>
<body
	onload="load('${itemForEdit.la}','${itemForEdit.lg}','${itemForEdit.nome}','${itemForEdit.p}')"
	onunload="GUnload()">

	<div align="center" id="map" style="width: 900px; height: 300px"></div>

	<div class="container">
		<div class="pontoRef">
			<h2>${itemForEdit.nome}</h2>
				<!--  	<c:if test="${not empty itemForEdit.estrelas}">
				<p>

					<c:set var="estrelasRot" value="${itemForEdit.estrelas}" />
					<%
						for (int j = 1; j <= (Integer) pageContext
									.getAttribute("estrelasRot"); j++) {
					%>
					<img class="no-margin" src="../../images/star.png" title="star"
						width="2%">
					<%
						}
					%>
				</p>

			</c:if>-->
			<div id="texto">
			<c:if test="${not empty itemForEdit.descricao}">
				<p>${itemForEdit.descricao}</p>
			</c:if>
			</div>

		<!--  	<p>Location: ${itemForEdit.la},${itemForEdit.lg}</p>-->


			<div class="right" >
			<c:if test="${not empty itemForEdit.p}">

				<p>Country: ${itemForEdit.p}</p>
			</c:if>
			</div>
		</div>
		</div>

		<h2>Events</h2>

		<div class="container">
			<c:forEach var="item1" items="${listaEventos}" varStatus="index">

				<c:if test="${not empty item1.nome}">

					<div class="box3">
						<h3>${item1.nome}</h3>
						<c:if test="${not empty item1.descricao}">
							<p>${item1.descricao}</p>
						</c:if>
						<c:url value="/pub/centrosInteresse/EventoAction.show"
							var="repoURL">
							<c:param name='ParentId' value='${item1.idEvento}' />

 </c:url>

						<a class=g href=${repoURL } title='Details'>Details</a>

            <c:if test="${not empty item1.idEvento}"><a class=g href=${repoURL } title='Details'>Details</a></c:if>

						<c:if test="${not empty item1.idEvento}">
							<a class=g href=${repoURL } title='Details'>Details</a>
						</c:if>


					</div>
				</c:if>
			</c:forEach>
		</div>


		<h2>Visits</h2>
		<div class="container">

			<c:forEach var="item1" items="${listaVisitas}" varStatus="index">
				<div class="box3">
					<h3>${item1.nome}</h3>
					<c:if test="${not empty item1.descricao}">
						<p>${item1.descricao}</p>
					</c:if>
					<c:url value="/pub/centrosInteresse/VisitaAction.show"
						var="repoURL">
						<c:param name='ParentId' value='${item1.idVisita}' />
					</c:url>
					<c:if test="${not empty item1.idVisita}">
						<a class=g href=${repoURL } title='details'>Details</a>
					</c:if>
				</div>
			</c:forEach>
		</div>

		<div class="comments" align="center">
			<p class=g>Let here your evaluation:</p>

			<form action="/situris/main/evaluation/EvaluationPontoAction.apply"
				method="POST" class="user-input" name='giveMeFocus'>

				<table>
					<tr>
						<td><label>Evaluation</label></td>
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
						<td><input type="hidden" name=idPontoRef
							value='${itemForEdit.idPontoRef}'></td>
					</tr>
					<tr>
						<td align="center" colspan=2><input type=submit value="Ok"></td>
					</tr>
				</table>
			</form>
		</div>

		<h2>Comments</h2>
		<div class="container">
			<c:forEach var="item2" items="${listaComentarios}" varStatus="index">
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
								width="2%">
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
	</div>
</body>