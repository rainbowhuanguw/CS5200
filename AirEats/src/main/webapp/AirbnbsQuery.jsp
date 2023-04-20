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
<nav class="navbar navbar-expand-lg navbar-dark bg-primary" style="padding-left:20px">
	  <a class="navbar-brand" href="#">AirEats</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
	    <div class="navbar-nav">
	      <a class="nav-item nav-link active" href="#">Index<span class="sr-only">(current)</span></a>
	      <a class="nav-item nav-link" href="RestaurantsNearby">Nearby</a>
	      <a class="nav-item nav-link" href="RestaurantsQuery">Restaurants</a>
	    </div>
	  </div>
	</nav>
	<div class="container-fluid" style="margin-top:20px">
    
	<form action="airbnbsquery" method="post">
	    <div class="jumbotron">
		<h1>Search for Airbnbs</h1>
		</div>
		<p>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">State</label>
			  </div>
			    <select id="state" name="state" class="custom-select" title="Select State">
				  <option selected value="">None</option>
				  <option value="CA">CA</option>
				  <option value="CO">CO</option>
				  <option value="DC">DC</option>
				  <option value="FL">FL</option>
				  <option value="HI">HI</option>
				  <option value="IL">IL</option>
				  <option value="LA">LA</option>
				  <option value="MA">MA</option>
				  <option value="MN">MN</option>
				  <option value="NC">NC</option>
				  <option value="NJ">NJ</option>
				  <option value="NV">NV</option>
				  <option value="NY">NY</option>
				  <option value="OH">OH</option>
				  <option value="OR">OR</option>
				  <option value="RI">RI</option>
				  <option value="TN">TN</option>
				  <option value="TX">TX</option>
				  <option value="WA">WA</option>
				</select>
			    <label class="input-group-text" for="inputGroupSelect01">City</label>
				<select id="city" name="city" class="custom-select" title="Select City">
				  <option selected value="">None</option>
				  <option class="CA">Los Angeles</option>
				  <option class="CA">Oakland</option>
				  <option class="CA">Pacific Grove</option>
				  <option class="CA">San Diego</option>
				  <option class="CA">San Francisco</option>
				  <option class="CA">San Mateo County</option>
				  <option class="CA">Santa Clara County</option>
				  <option class="CA">Santa Cruz County</option>
				  <option class="CO">Denver</option>
				  <option class="DC">Washington D.C.</option>
				  <option class="FL">Broward</option>
				  <option class="HI">Hawaii</option>
				  <option class="IL">Chicago</option>
				  <option class="LA">New Orleans</option>
				  <option class="MA">Boston</option>
				  <option class="MA">Cambridge</option>
				  <option class="MN">Twin Cities MSA</option>
				  <option class="NC">Asheville</option>
				  <option class="NJ">Jersey City</option>
				  <option class="NJ">Newark</option>
				  <option class="NV">Clark County</option>
				  <option class="NY">New York City</option>
				  <option class="OH">Columbus</option>
				  <option class="OR">Portland</option>
				  <option class="OR">Salem</option>
				  <option class="RI">Rhode Island</option>
				  <option class="TN">Nashville</option>
				  <option class="TX">Austin</option>
				  <option class="TX">Dallas</option>
				  <option class="TX">Fort Worth</option>
				  <option class="WA">Seattle</option>
				</select>
				    <span class="input-group-text" id="inputGroup-sizing-default">Airbnb Id</span>
				<input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" id="airbnbId" name="airbnbId" value="${fn:escapeXml(param.airbnbId)}" />
				<span class="input-group-text" id="inputGroup-sizing-default">Airbnb Name</span>
					<input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" id="airbnbName" name="airbnbName" value="${fn:escapeXml(param.airbnbName)}" />
				<div class="input-group-append">
			    <button type="submit" class="btn btn-outline-secondary" type="button">Submit</button>
			  </div>
				</div>
			  	
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
                    <td><a href="RestaurantsNearby?airbnb_id=<c:out value="${airbnb.getAirbnbId()}"/>&radius=100&page=1&itemsPerPage=25">Detail</a></td>
                </tr></tbody>
            </c:forEach>
       </table>
       </div>
     
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
    <script>
	  $("#city option").hide()
    	$('#state').on('change', function(e) {
    	  var values = $(this).val()
    	  $("#city option").hide() //hide all options
    	  $("#city ." + values).show()
    	});
    </script>
</body>
</html>