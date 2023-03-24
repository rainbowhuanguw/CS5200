<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Create a Airbnb</title>
</head>
<body>
	<h1>Create Airbnb</h1>
	<form action="airbnbcreate" method="post">
		<p>
			<label for="airbnbId">AirbnbId</label>
			<input id="airbnbId" name="airbnbId" value="">
		</p>
		<p>
			<label for="hostId">HostId</label>
			<input id="hostId" name="hostId" value="">
		</p>
        <p>
			<label for="name">Name</label>
			<input id="name" name="name" value="">
		</p>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="neighborhood">Neighborhood</label>
			<input id="neighborhood" name="neighborhood" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="statee" value="">
		</p>
		<p>
			<label for="latitude">latitude</label>
			<input id="latitude" name="latitude" value="">
		</p>
		<p>
			<label for="longitude">longitude</label>
			<input id="longitude" name="longitude" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>