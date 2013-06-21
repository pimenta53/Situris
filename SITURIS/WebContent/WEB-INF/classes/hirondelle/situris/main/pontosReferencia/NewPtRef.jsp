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
			var center = new GLatLng(41.5517605, -8.4229034);
			map.setCenter(center, 11);
			geocoder = new GClientGeocoder();

			var icon = new GIcon(G_DEFAULT_ICON);
			icon.image = "../../images/largeTDRedIcons/blank.png";
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
				icon.image = "../../images/largeTDRedIcons/blank.png";
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
									icon.image = "../../images/largeTDRedIcons/blank.png";
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
														icon.image = "../../images/largeTDRedIcons/blank.png";
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
		<input type="text" size="34" name="address" value="Braga, Portugal" />
		<input type="submit" value="Search!" />
	</form>
	<br>
	<div align="center" id="map" style="width: 900px; height: 300px"></div>
	</div>

	<br />


	<c:set value='NewPtRefAction.apply' var='baseURL' />
	<w:txtFlow>
		<form action='${baseURL}' method="post" class="user-input">
			<table align="center">
				<tr>
					<td><label>Name</label></td>
					<td><input name="Nome" type="text"></td>
				</tr>
				<tr>
					<td><label>Description</label></td>
					<td><textarea name="Descricao" alt='Max 255 characters rows='
							6'  cols='30'></textarea></td>
				</tr>
				<tr>
					<td><label>Country</label></td>
					<td><select name="Pais">
							<c:forEach var="itemsListing" items="${itemsForListing}"
								varStatus="index">
								<option value='${itemsListing.idPais}'>${itemsListing.nomePais}</option>
							</c:forEach>
					</select></td>
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
					<td align="center" colspan=2><input type="submit"
						value="Create"></td>
				</tr>
			</table>
		</form>
	</w:txtFlow>