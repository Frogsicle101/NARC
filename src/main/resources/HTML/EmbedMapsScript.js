let map;

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 41.881832, lng: -87.623177 },
    zoom: 8,
  });


}

function addMarkers(markers) {
  for (let i = 0; i < markers.length; i++) {
    new google.maps.Marker({
      position: markers[i],
      map: map,
    });
  }
}
