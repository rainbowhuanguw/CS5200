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
</head>
<body>
    <div class="container theme-showcase" role="main">
    
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
			<input id="radius" name="radius" value="${fn:escapeXml(param.radius)}">
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
            </tr></thead>
            <c:forEach items="${restaurants}" var="restaurant" >
                <tbody><tr>
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
