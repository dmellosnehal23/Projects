var geocoder;
var map;
var bounds;
var initialized = false;

function initialize() { // DONE
	geocoder = new google.maps.Geocoder();

	var mapOptions = {
		zoom : 12,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}

	map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	bounds = new google.maps.LatLngBounds();
	
	initialized = true;
}


function plotLocationByLatLon(lat, lon, title, iconImg, centered) {
	if (!initialized) {
		initialize();
	}


	var position = new google.maps.LatLng(lat, lon);
	var marker = new google.maps.Marker({
		position : position,
		map : map,
	});
	
	
	if(title) {
		marker.setTitle(title);
		// add click listener -
		// https://developers.google.com/maps/documentation/javascript/events
		google.maps.event.addListener(marker, 'click', function() {
			// http://css-tricks.com/snippets/javascript/get-url-and-url-parts-in-javascript/
			var url = window.location.protocol + "//" + "realmashup.aws.af.cm" + "/restClient/getProperties?watchlist=false&query=" + title;
			window.open(url, "_self");
		});
	}
	
	if(iconImg) {
		var icon = new google.maps.MarkerImage(iconImg);
		marker.setIcon(icon);
	}
	
	if(centered) {
		map.setCenter(position);
	} else {
	
		// https://developers.google.com/maps/documentation/javascript/reference?csw=1#LatLngBounds
		bounds.extend(position);
		map.fitBounds(bounds);
	}
}

function plotLocationByAddress(address, title, iconImg, centered) {
	if (!initialized) {
		initialize();
	}

	// function written to plot the city/address i.e Red marker
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if(centered) {
			    map.setCenter(results[0].geometry.location);
			}
			var marker = new google.maps.Marker({
				map : map,
				position : results[0].geometry.location
			});
			
			if(title) {
				marker.setTitle(title);
			}
			if(iconImg) {
				var icon = new google.maps.MarkerImage(iconImg);
				marker.setIcon(icon);
			}
		} else {
			console.log('Geocode was not successful for the following reason: '
					+ status);
		}
	});
}

function plotProperties(properties, marker) {
	for (var i = 0; i < properties.length; i++) {
		var lat = properties[i].latitude;
		var lon = properties[i].longitude;
		var address = properties[i].address;
		
		plotLocationByLatLon(lat, lon, address, marker, false); 
	}

}

function plotNeighborhoods(neighborhoods, markers) {
	
	var types = ['hospitals', 'schools', 'restaurants', 'publicTransits', 'groceryStores', 'cinemas'];
	//var types = ['schools', 'groceryStores'];
	types.forEach( function(type) {
	
		for (var i = 0; i < neighborhoods[type].length; i++) {
			var name = neighborhoods[type][i].name;
			var address = neighborhoods[type][i].address;
			plotLocationByAddress(address, name, markers[type], false);
			
			//for geocoding limit
			if(i > 0) break;
		}
	});
	
}

function plotPropertyWithNeighborhoods(lat, lon, title, markers) {
	//plot home centered
    plotLocationByLatLon(lat, lon, title, null, true);
    
    //make REST call to get neighborhood
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//listner 
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//http://stackoverflow.com/questions/14249268/having-trouble-with-my-json-http-request
			plotNeighborhoods(JSON.parse(xmlhttp.responseText), markers);
		}
	}

	var url = window.location.protocol + "//" + "realmashup.aws.af.cm" + "/rest/neighborhood?lat=" + lat + "&lon=" + lon;
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}


