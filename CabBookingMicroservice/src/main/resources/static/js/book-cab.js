/**
 * 
 */

var map, directionsService, directionsRenderer;

		function initMap() {
			// Initialize the map centered on a default location
			map = new google.maps.Map(document.getElementById('map'), {
				center: {lat: 37.7749, lng: -122.4194},  // San Francisco as default
				zoom: 13
			});

			// Initialize the Directions and Distance services
			directionsService = new google.maps.DirectionsService();
			directionsRenderer = new google.maps.DirectionsRenderer();
			directionsRenderer.setMap(map);
		}


		function showRoute(origin, destination) {
			var request = {
				origin: origin,
				destination: destination,
				travelMode: 'DRIVING'
			};

			directionsService.route(request, function (result, status) {
				if (status == 'OK') {
					directionsRenderer.setDirections(result);
				} else {
					document.getElementById('output').innerHTML = 'Could not display directions due to: ' + status;
				}
			});
		}

		function callEndpoint() {
			// Get the selected radio button value

			const formData = {
				fromLocation: document.getElementById("fromLocation").value,
				toLocation: document.getElementById("toLocation").value,
				cabType: document.querySelector('input[name="cabType"]:checked').value
			};
			//const selectedValue = document.querySelector('input[name="cabType"]:checked').value;

			// Make an AJAX request to the Spring Boot endpoint
			fetch('/cabBooking/cabType', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(formData),
			})
				.then(response => response.json())
				.then(data => {
					// Handle the response from the endpoint	

					document.getElementById('responseMessage').innerText = 'Fare: ' + "$" + data.fare;
					document.getElementById('fare').value = data.fare;
					
					document.getElementById('output').innerHTML =
						'Distance(In Miles): ' + data.distance + '<br>Duration: ' + data.duration;
					showRoute(document.getElementById('fromLocation').value, document.getElementById('toLocation').value); calculateDistanceAndRoute

				})
				.catch(error => {
					// Handle any errors
					console.error(error);

				});
		}
				
			function callCalculateFareEndpoint() {
			// Get the selected radio button value			
			const formData = {
				fromLocation: document.getElementById("fromLocation").value,
				toLocation: document.getElementById("toLocation").value				
			};			
			// Make an AJAX request to the Spring Boot endpoint
			fetch('/cabBooking/displayFare', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(formData),
			})
				.then(response => response.json())
				.then(data => {
					// Handle the response from the endpoint
					document.getElementById('economy').innerHTML = '<img src="/images/economyCab.jpg">'+'Economy Cab Fare: ' + "$" + data.economy;
					document.getElementById('premium').innerHTML = '<img src="/images/premiumCab.jpg">'+'Premium Cab Fare: ' + "$" + data.premium;
					document.getElementById('luxury').innerHTML = '<img src="/images/luxuryCab.jpg">' +'Luxury Cab Fare: '+ "$" + data.luxury;	
					document.getElementById('distanceandduration').innerHTML =
						'Distance(In Miles): ' + data.distance + '<br>Duration: ' + data.duration;
					showRoute(document.getElementById('fromLocation').value, document.getElementById('toLocation').value); calculateDistanceAndRoute

				})
				.catch(error => {
					// Handle any errors
					console.error(error);

				});
		}
