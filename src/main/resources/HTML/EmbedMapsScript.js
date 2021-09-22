let map;
let mapMarkers = [];
let return_location = { lat: 41.85, lng: -87.65 };
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
  map.setZoom(16);
  return_location = {lat: map.getCenter().lat(), lng: map.getCenter().lng()};
  callscript();
}

function getLocation() {
  return return_location;
}

function addMarkers(markers) {
  for (let i = 0; i < markers.length; i++) {
    mapMarkers[i] =
    new google.maps.Marker({
      position: markers[i],
      map: map,
      icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
    });
  }
}

function addMarker(marker) {
  mapMarkers.push(
    new google.maps.Marker({
      position: marker[0],
      map: map,
      icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
    })
  );
}

function removeMarkers() {
  for (let i = 0; i < mapMarkers.length; i++) {
    mapMarkers[i].setMap(null);
  }
  mapMarkers = [];
}

function addLocationMarker() {
  locationMarker = new google.maps.Marker({
    position: return_location,
    map: map,
    title: "Centre",
    icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
  });
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