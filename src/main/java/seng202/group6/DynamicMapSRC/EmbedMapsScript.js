let map;
let mapMarkers = [];
let returnLocation = { lat: 41.85, lng: -87.65 };
let currentAddress;
let locationMarker;

function callscript() {
  app.callJavascript();
}

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 41.85, lng: -87.65 },
    zoom: 10,
    mapId: '6a20fe312aa3d2af',
    streetViewControl: false,
    fullscreenControl: false,
    mapTypeControl: false,
  });

  const card = document.getElementById("ac-card");
  const input = document.getElementById("autocomplete");
  const options =     {
    types: ["address"],
    fields: ["formatted_address", "geometry", "name"],
  };

  map.controls[google.maps.ControlPosition.TOP_LEFT].push(card);
  autocomplete = new google.maps.places.Autocomplete(input, options);

  autocomplete.addListener('place_changed', onPlaceChanged);
}

function onPlaceChanged() {
  const place = autocomplete.getPlace();
  map.setCenter(place.geometry.location);
  map.setZoom(17);
  returnLocation = {lat: map.getCenter().lat(), lng: map.getCenter().lng()};
  currentAddress = document.getElementById("autocomplete").value;
  callscript();
}

function getLocation() {
  return returnLocation;
}

function addMarker(crim) {
  const contentString =
    '<div id="content">' +
    '<div id="bodyContent">' +
    "<p>Case Number: "+crim[3].id+"</p>" +
    "<p>Type: "+crim[1].crime+"</p>" +
    "<p>Date: "+crim[2].date+"</p>" +
    "<button onclick=\"viewMoreInfo('"+crim[3].id+"')\">View More Information</button>" +
    "</div>" +
    "</div>";
  const infowindow = new google.maps.InfoWindow({
    content: contentString,
  });
  const marker = new google.maps.Marker({
      position: crim[0],
      map: map,
      icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
    });
  marker.addListener("click", () => {
    infowindow.open({
      anchor: marker,
      map,
      shouldFocus: false,
    });
  });
  mapMarkers.push(marker);
}

function removeMarkers() {
  for (let i = 0; i < mapMarkers.length; i++) {
    mapMarkers[i].setMap(null);
  }
  mapMarkers = [];
}

function addLocationMarker() {
    const contentString =
    '<div id="content">' +
    '<div id="bodyContent">' +
    "<p>Address: "+currentAddress+"</p>" +
    "</div>" +
    "</div>";
  const infowindow = new google.maps.InfoWindow({
    content: contentString,
  });

  const marker = new google.maps.Marker({
    position: returnLocation,
    map: map,
    title: "Centre",
    icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
  });

  marker.addListener("click", () => {
    infowindow.open({
      anchor: marker,
      map,
      shouldFocus: false,
    });
  });
  locationMarker = marker;
}

function removeLocationMarker() {
    if (locationMarker != null) {
    locationMarker.setMap(null);
    locationMarker = null;
  }
}

function getLocationZoom() {
  return map.getZoom();
}

function viewMoreInfo(crimid) {
  app.viewInfo(crimid);
}