<script
	src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyDrBSpUZiez2WsUYed8vGyPgbxF9LyRHx0"
	type="text/javascript"></script>

<script type="text/javascript">
	function load() {
		if (GBrowserIsCompatible()) {
			var map = new GMap2(document.getElementById("map"));
			map.addControl(new GOverviewMapControl());
			map.enableDoubleClickZoom();
			map.enableScrollWheelZoom();
			map.addControl(new GMapTypeControl());
			map.addControl(new GSmallMapControl());
			var center = new GLatLng(document.getElementById("lat").value, document.getElementById("long").value);
			map.setCenter(center, 11);
			geocoder = new GClientGeocoder();

			var icon = new GIcon(G_DEFAULT_ICON);
			icon.image = "../../images/largeTDBlueIcons/blank.png";
			var marker = new GMarker(center, {
				icon : icon,
				draggable : true
			});


			map.addOverlay(marker);
			document.getElementById("lat").value = center.lat();
			document.getElementById("long").value = center.lng();

			geocoder = new GClientGeocoder();

			GEvent.addListener(marker, "dragend", function() {
				var point = marker.getPoint();
				map.panTo(point);
				document.getElementById("lat").value = point.lat();
				document.getElementById("long").value = point.lng();
			});

			GEvent.addListener(map, "moveend", function() {
				map.clearOverlays();
				var center = map.getCenter();

				var icon = new GIcon(G_DEFAULT_ICON);
				icon.image = "../../images/largeTDBlueIcons/blank.png";
				var marker = new GMarker(center, {
					icon : icon,
					draggable : true
				});


				map.addOverlay(marker);
				document.getElementById("lat").value = center.lat();
				document.getElementById("long").value = center.lng();

				GEvent.addListener(marker, "dragend", function() {
					var point = marker.getPoint();
					map.panTo(point);
					document.getElementById("lat").value = point.lat();
					document.getElementById("long").value = point.lng();
				});
			});
		}
	}

	function showAddress(address) {
		var map = new GMap2(document.getElementById("map"));
		map.addControl(new GOverviewMapControl());
		map.enableDoubleClickZoom();
		map.enableScrollWheelZoom();
		map.addControl(new GMapTypeControl());
		map.addControl(new GSmallMapControl());
		map.setMapType(G_HYBRID_MAP);
		if (geocoder) {
			geocoder
					.getLatLng(
							address,
							function(point) {
								if (!point) {
									alert(address + " city not found !");
								} else {
									document.getElementById("lat").value = point
											.lat();
									document.getElementById("long").value = point
											.lng();
									map.clearOverlays()
									map.setCenter(point, 14);

									var icon = new GIcon(G_DEFAULT_ICON);
									icon.image = "../../images/largeTDBlueIcons/blank.png";
									var marker = new GMarker(point, {
										icon : icon,
										draggable : true
									});

									map.addOverlay(marker);

									GEvent
											.addListener(
													marker,
													"dragend",
													function() {
														var pt = marker
																.getPoint();
														map.panTo(pt);
														document
																.getElementById("lat").value = pt
																.lat();
														document
																.getElementById("long").value = pt
																.lng();
													});

									GEvent
											.addListener(
													map,
													"moveend",
													function() {
														map.clearOverlays();
														var center = map
																.getCenter();

														var icon = new GIcon(
																G_DEFAULT_ICON);
														icon.image = "../../images/largeTDBlueIcons/blank.png";
														var marker = new GMarker(
																center,
																{
																	icon : icon,
																	draggable : true
																});

														map.addOverlay(marker);
														document
																.getElementById("lat").value = center
																.lat();
														document
																.getElementById("long").value = center
																.lng();

														GEvent
																.addListener(
																		marker,
																		"dragend",
																		function() {
																			var pt = marker
																					.getPoint();
																			map
																					.panTo(pt);
																			document
																					.getElementById("lat").value = pt
																					.lat();
																			document
																					.getElementById("long").value = pt
																					.lng();
																		});
													});
								}
							});
		}
	}
</script>
<body onload="load()" onunload="GUnload()">

	<br>
	<b>Type city name:</b>
	<br>
	<form action="#"
		onsubmit="showAddress(this.address.value); return false">
		<input type="text" size="34" name="address" value="" />
		<input type="submit" value="Search!" />
	</form>
	<br>
	<div align="center" id="map" style="width: 900px; height: 300px"></div>
	</div>

	<br />


	<c:set value='EventoAction.change' var='baseURL' />
	<w:txtFlow>
		<w:populate using="itemForEdit">
			<form action='${baseURL}' method="post" class="user-input" enctype="multipart/form-data">
				<input name="IdEvento" type="hidden">
				<table align="center">
					<tr>
						<td><label>Name</label></td>
						<td><input name="Nome" type="text"></td>
					</tr>
					<tr>
						<td><label>Description</label></td>
						<td><textarea name="Descricao" alt='Max 255 characters'
								rows='6' cols='30'></textarea></td>
					</tr>
					<tr>
						<td><label>Link</label></td>
						<td><input name="Link" type="text" size="40"></td>
					</tr>
          <tr>
            <td><label>Image File</label></td>
            <td><input name="ImageFile" type="file"></td>
          </tr>
          <tr>          
					<tr>
						<td><label>Begin Date</label></td>
						<td><input name="DataInicioTB" type="text">
							(YYYYMMDD)</td>
					</tr>
					<tr>
						<td><label>End Date</label></td>
						<td><input name="DataFimTB" type="text"> (YYYYMMDD)</td>
					</tr>
					<tr>
					<tr>
						<td><label>Interest Type</label></td>
						<td><select name="TipoInteresse">
								<c:forEach var="itemsListing" items="${itemsForListing}"
									varStatus="index">
									<option value='${itemsListing.idTipoInteresse}'>${itemsListing.descricaoTI}</option>
								</c:forEach>
						</select></td>
					</tr>
          <tr>
            <td><input name="idCoord" type="hidden"></td>
          </tr>
					<tr>
						<td><label>Latitude</label></td>
						<td><input name="Latitude" type="text" id="lat"></td>
					</tr>
					<tr>
						<td><label>Longitude</label></td>
						<td><input name="Longitude" type="text" id="long"></td>
					</tr>
					<tr>
						<td><input name="Altitude" type="hidden" id="alt"></td>
					</tr>
					<tr>
						<td><label>Reference Point</label></td>
						<td><select name="PontoRef">
								<c:forEach var="itemsListing2" items="${ptrefs}"
									varStatus="index">
									<option value='${itemsListing2.idPontoRef}'>${itemsListing2.nomePontoRef}</option>
								</c:forEach>
						</select></td>
					</tr>
					</w:populate>
					<tr>
						<td align="center" colspan=2><input type="submit"
							value="Update"></td>
					</tr>
				</table>
  <input name="Operation" type='hidden' value='Change'>
			</form>
	</w:txtFlow>