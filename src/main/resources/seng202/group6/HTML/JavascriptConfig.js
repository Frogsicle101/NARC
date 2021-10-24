function getApiKey() {
  var script = document.createElement("script");
  script.src = "https://maps.googleapis.com/maps/api/js?key=YOU_KEY&libraries=places&callback=initMap&v=weekly";
  script.async;
  document.body.appendChild(script);
}

