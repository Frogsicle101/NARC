function getApiKey() {
  var script = document.createElement("script");
  script.src = "https://maps.googleapis.com/maps/api/js?key=AIzaSyBZgxE6A5nvnM7aYqg49wDdK_SPKXqdLiE&libraries=places&callback=initMap&v=weekly";
  script.async;
  document.body.appendChild(script);
}

