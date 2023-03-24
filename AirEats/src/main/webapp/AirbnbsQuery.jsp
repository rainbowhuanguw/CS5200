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
<title>Query Airbnbs</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
    
	<form action="airbnbsquery" method="post">
	    <div class="jumbotron">
		<h1>Search for Airbnbs by name</h1>
		</div>
		<p>
			<h2><label for="airbnbName">AirbnbName</label></h2>
			<input id="airbnbName" name="airbnbName" value="${fn:escapeXml(param.airbnbName)}">
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
	<h1>Matching Airbnbs</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>Id</th>
                <th>HostId</th>
                <th>Name</th>
                <th>City</th>
                <th>Neightborhood</th>
                <th>State</th>
                <th>Latitude</th>
                <th>Longitude</th>
            </tr></thead>
            <c:forEach items="${airbnbs}" var="airbnb" >
                <tbody><tr>
                    <td><c:out value="${airbnb.getAirbnbId()}" /></td>
                    <td><c:out value="${airbnb.getHostId()}" /></td>
                    <td><c:out value="${airbnb.getName()}" /></td>
                    <td><c:out value="${airbnb.getCity()}" /></td>
                    <td><c:out value="${airbnb.getNeighborhood()}" /></td>
                    <td><c:out value="${airbnb.getState()}" /></td>
                    <td><c:out value="${airbnb.getLatitude()}" /></td>
                    <td><c:out value="${airbnb.getLongitude()}" /></td>
                    
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
