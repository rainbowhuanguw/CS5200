<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Query nearby Restaurants by Airbnb</title>

<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <style>

      #map {
        width: 70%;
  		height: 70%;
  		margin-left: auto;
  		margin-right: auto;
      }
      html,
      body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
</head>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary" style="padding-left:20px">
        <a class="navbar-brand" href="#">AirEats</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav">
            <a class="nav-item nav-link" href="airbnbsquery">Index</a>
            <a class="nav-item nav-link active" href="#">Nearby<span class="sr-only">(current)</span></a>
            <a class="nav-item nav-link" href="RestaurantsQuery">Restaurants</a>
          </div>
        </div>
      </nav>
<body>
    <div class="container fluid" role="main">
    
	<form action="RestaurantsNearby" method="post">
	    <div class="jumbotron">
		<h1>Search for nearby Restaurant</h1>
		</div>
		<p>
			<h2><label for="airbnb_id">AirbnbId</label></h2>
			<input id="airbnb_id" name="airbnb_id" value="${fn:escapeXml(param.airbnb_id)}">
		</p>
		<p>
			<h2><label for="radius">Radius</label></h2>
			<input id="radius" name="radius" value="${fn:escapeXml(param.radius)}" maxlength="3" oninput="checkInput(this)">
			<p id="inputError"></p>
			<script>
				function checkInput(input) {
				  let value = parseInt(input.value);
				  let error = document.getElementById("inputError");
				  
				  if (isNaN(value) || value < 1 || value > 100) {
				    error.textContent = "Please set the radius between 1 and 70.";
				    input.setCustomValidity("Invalid input.");
				  } else {
				    error.textContent = "";
				    input.setCustomValidity("");
				  }
				}
			</script>
		</p>
		
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/>
		</p>
	</form>
	<br/>
	
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
       
          
 	<br/>
 	</div>
    
    <div id="map"></div>
    <script>
      
      function initMap() {
    	
        const map = new google.maps.Map(document.getElementById("map"), {
          zoom: 10,
          center:  { lat: 0.0, lng: 0.0},
          scale: 2,
        });
 
        setMarkers(map);
       
      }


      window.initMap = initMap;
      
  
      function setMarkers(map) {
        // Adds markers to the map.
        // Marker sizes are expressed as a Size of X,Y where the origin of the image
        // (0,0) is located in the top left of the image.
        // Origins, anchor positions and coordinates of the marker increase in the X
        // direction to the right and in the Y direction down.
        const bounds = new google.maps.LatLngBounds();
        const image = {
          url: "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png",
          // This marker is 20 pixels wide by 32 pixels high.
          size: new google.maps.Size(20, 32),
          // The origin for this image is (0, 0).
          origin: new google.maps.Point(0, 0),
          // The anchor for this image is the base of the flagpole at (0, 32).
          anchor: new google.maps.Point(0, 32),
        };
        // Shapes define the clickable region of the icon. The type defines an HTML
        // <area> element 'poly' which traces out a polygon as a series of X,Y points.
        // The final coordinate closes the poly by connecting to the first coordinate.
        const shape = {
          coords: [1, 1, 1, 20, 18, 20, 18, 1],
          type: "poly",
        };
        
        
        const restaurants = new Array();
        <c:forEach items="${restaurants}" var="restaurant" varStatus="status">
     		restaurant = new Object();
     		
     		restaurant.name = "${restaurant.getName()}";
     		restaurant.latitude = parseFloat(${restaurant.getLatitude()});
     		restaurant.longitude = parseFloat(${restaurant.getLongitude()});
  			restaurant.description = "${restaurant.getDescription()}"; 
  			
     		restaurants.push(restaurant);
     		center = new google.maps.LatLng(restaurant.latitude, restaurant.longitude);
     		bounds.extend(center);
        </c:forEach>
        for (let i = 0; i < restaurants.length; i++) {
          const restaurant = restaurants[i];

          const marker = new google.maps.Marker({
            position: { lat: restaurant.latitude, lng: restaurant.longitude },
            map,
            icon: image,
            shape: shape,
            title: restaurant.name,
          });
          const contentString =
        	    '<div id="content">' +
        	    '<div id="siteNotice">' +
        	    "</div>" +
        	    '<h1 id="firstHeading" class="firstHeading">' + restaurant.name + '</h1>' +
        	    '<div id="bodyContent">' +
        	    "<p>" + restaurant.description + "</p>" +
        	    "</div>" +
        	    "</div>";
          const infowindow = new google.maps.InfoWindow({
        	    content: contentString,
        	    ariaLabel: restaurant.name,
        	  });
          marker.addListener("click", () => {
        	    infowindow.open({
        	      anchor: marker,
        	      map,
        	    });
        	  });
        }
        
        const airbnb = new Object();
        <c:forEach items="${airbnbs}" var="airbnb" varStatus="status">
        	airbnb.name = "${airbnb.getName()}";
	    	airbnb.latitude = parseFloat(${airbnb.getLatitude()});
	    	airbnb.longitude = parseFloat(${airbnb.getLongitude()});
	    	
	    	center = new google.maps.LatLng(airbnb.latitude, airbnb.longitude);
	    	bounds.extend(center);
		</c:forEach>
		
		const marker = new google.maps.Marker({
            position: { lat: airbnb.latitude, lng: airbnb.longitude },
            map,
            title: airbnb.name,
          });
          const contentString =
        	    '<div id="content">' +
        	    '<div id="siteNotice">' +
        	    "</div>" +
        	    '<h1 id="firstHeading" class="firstHeading">' + airbnb.name + '</h1>' +
        	    '<div id="bodyContent">' +
        	    "<p>" + "</p>" +
        	    "</div>" +
        	    "</div>";
          const infowindow = new google.maps.InfoWindow({
        	    content: contentString,
        	    ariaLabel: airbnb.name,
        	  });
          marker.addListener("click", () => {
        	    infowindow.open({
        	      anchor: marker,
        	      map,
        	    });
        	  });
        
        map.fitBounds(bounds);
        map.panToBounds(bounds);
      }
    </script>
     <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDK_KC2eneg0m7ZNJatdNJ7Rgh9-5HRQ9U&callback=initMap&v=weekly"
      defer
    ></script>
    
    <br/>
	<h1>Nearby Restaurants</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>Name</th>
                <th>Id</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Zip</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Stars</th>
                <th>Link</th>
            </tr></thead>
            <c:forEach items="${restaurants}" var="restaurant" >
                <tbody><tr>
                    <td><a href="RestaurantsQuery?restaurant_id=<c:out value="${restaurant.getRestaurantId()}"/>">${restaurant.getName()}</a></td>
                    <td><c:out value="${restaurant.getName()}" /></td>
                    <td><c:out value="${restaurant.getRestaurantId()}" /></td>
                    <td><c:out value="${restaurant.getAddress()}" /></td>
                    <td><c:out value="${restaurant.getCity()}" /></td>
                    <td><c:out value="${restaurant.getState()}" /></td>
                    <td><c:out value="${restaurant.getZip()}" /></td>
                    <td><c:out value="${restaurant.getLatitude()}" /></td>
                    <td><c:out value="${restaurant.getLongitude()}" /></td>
                    <td><c:out value="${restaurant.getStars()}" /></td>
                </tr></tbody>
            </c:forEach>
       </table>
       </div>
    
    
       
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
       
</body>
</html>