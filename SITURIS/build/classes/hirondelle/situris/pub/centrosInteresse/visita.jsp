<script
	src=" http://maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyDrBSpUZiez2WsUYed8vGyPgbxF9LyRHx0"
	type="text/javascript">

</script>
<script type="text/javascript"
	src="http://ecn.dev.virtualearth.net/mapcontrol/mapcontrol.ashx?v=7.0"></script>

<script type="text/javascript">
	function load(latitude, longitude, nome, descicao, tipo, link) {
		if (GBrowserIsCompatible()) {
			var map = new GMap2(document.getElementById("map"));
			map.addControl(new GOverviewMapControl());
			map.enableDoubleClickZoom();
			map.enableScrollWheelZoom();
			map.addControl(new GMapTypeControl());
			map.addControl(new GSmallMapControl());
			var center = new GLatLng(latitude, longitude);
			map.setCenter(center, 11);
			geocoder = new GClientGeocoder();

			// Create our "tiny" marker icon
			var icon = new GIcon(G_DEFAULT_ICON);
			icon.image = "../../images/largeTDGreenIcons/blank.png";
			var marker = new GMarker(center, {
				icon : icon
			});

			GEvent.addListener(marker, "click", function() {
				marker.openInfoWindowHtml("<b>Visit - </b><b>" + nome + "</b><br/>" + tipo + "<br/>");
			});
			marker.openInfoWindowHtml("<b>Visit - </b><b>" + nome
					+ "</b><br/>" + tipo);

			map.addOverlay(marker);
			document.getElementById("lat").value = center.lat();
			document.getElementById("lng").value = center.lng();

			geocoder = new GClientGeocoder();
		}
	}
</script>
	<div id="titulo"><h4>${itemForEdit.nome}</h4></div>
	<br/>
<body
	onload="load('${itemForEdit.latitude}','${itemForEdit.longitude}','${itemForEdit.nome}','${itemForEdit.descricao}','${itemForEdit.descricaoTI}','${itemForEdit.link}')"
	onunload="GUnload()">
	<div align="center" id="map" style="width: 900px; height: 300px"></div>
	<div class="container">

		<div class="box3">
		<div class="right">

				<c:if test="${not empty itemForEdit.estrelas}">
				<c:set var="estrelasVisita" value="${itemForEdit.estrelas}" />

				<%for (int i = 1; i <= (Integer) pageContext.getAttribute("estrelasVisita"); i++) {%>
				  <img class="no-margin" src="../../images/star.png" title="star" width="18px">
				<%}%>
			</c:if>
				<br/>
			 <c:if test="${not empty itemForEdit.descricaoTI}">
       ${itemForEdit.descricaoTI}
      </c:if>

			</div>


      <br/>
      <c:if test="${not empty itemForEdit.descricao}">
      ${itemForEdit.descricao}
      </c:if>
      <c:if test="${not empty itemForEdit.imagem}">
      <p><img class="no-margin" src="../../images/visits/${itemForEdit.imagem}" title="imagem" width="2%"></p>
      </c:if>
      <c:if test="${not empty itemForEdit.link}">
			<p><a href="${itemForEdit.link}">${itemForEdit.link}</a></p>
      </c:if>
		<!--  	<p>Location: ${itemForEdit.latitude}, ${itemForEdit.longitude}</p>-->

		</div>
		<div class="comments" align="center">
			<p class=g>Let here your evaluation:</p>
			<form action="/situris/main/evaluation/EvaluationVisitaAction.apply"
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
						<td><input type="hidden" name=idVisita
							value='${itemForEdit.idVisita}'></td>
					</tr>
					<tr>
						<td align="center" colspan=2><input type=submit value="Ok">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<h2>Comments</h2>
		<div class="container">
			<c:forEach var="item2" items="${itemsForListing}" varStatus="index">
				<div class="box5">
					<div class="g">By ${item2.nomeUtilizador} on ${item2.data}</div>
					<c:if test="${not empty item2.estrelas}">
						<c:set var="estrelasVisita" value="${itemForEdit.estrelas}" />
				    Evaluation:
				    <%for (int i = 1; i <= (Integer) pageContext.getAttribute("estrelasVisita"); i++) {%>
						  <img class="no-margin" src="../../images/star.png" title="star" width="2%">
						<%}%>
					</c:if>
					<c:if test="${not empty item2.comentario}">
            <p>Comment: ${item2.comentario}</p>
          </c:if>
				</div>
			</c:forEach>
		</div>

		<br />
</body>