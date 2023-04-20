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
    <title>Query a Restaurant</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary" style="padding-left:20px">
    <a class="navbar-brand" href="#">AirEats</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="airbnbsquery">Index</a>
            <a class="nav-item nav-link" href="RestaurantsNearby">Nearby</a>
            <a class="nav-item nav-link active" href="#">Restaurants<span class="sr-only">(current)</span></a>
        </div>
    </div>
</nav>

<div class="container-fluid" style="margin-top:20px">

    <form action="RestaurantsQuery" method="get">
        <div class="jumbotron">
            <h1>Search for a Restaurant</h1>
        </div>
        <p>
        <h2><label for="restaurant_id">RestaurantId</label></h2>
        <input id="restaurant_id" name="restaurant_id" value="${fn:escapeXml(param.restaurant_id)}">
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

    <h2>Restaurant Information</h1>
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

        <div class = 'row'>
            <div class="card-column" style =
                    "float: left;
			        width: 60%;">
                <h2>Restaurant Reviews</h2>
                <c:forEach items="${reviews}" var="review" >
                    <div class="card text-center" style="width: 60rem;">
                        <div class="card-body">
                            <h5>UserId: ${review.getReview().getUserId()}</h5>
                            <p>Restaurant id:  ${review.getReview().getRestaurantId()}</p>
                            <p>Stars: ${review.getReview().getStars()}</p>
                            <p>Useful: ${review.getReview().getUseful()}     Funny: ${review.getReview().getFunny()}   Cool: ${review.getReview().getCool()}</p>
                            <p>Content: ${review.getReview().getContent()}</p>
                            <p>Date: ${review.getReview().getDate()}</p>
                        </div>
                    </div>
                </c:forEach>

            </div>

            <div class="card-column" style =
                    "float: right;
			        width: 40%;">

                <h2>Restaurant Tips</h2>
                <c:forEach items="${tips}" var="tip" >
                    <div class="card text-center" style="width: 40rem;">
                        <div class="card-body">
                            <h5 class="card-title">UserId: ${tip.getUser().getUserId()}</h5>
                            <p class="card-compliment">Compliment: ${tip.getTips().getComplimentCount()}</p>
                            <p class="card-context">Content: ${tip.getTips().getContext()}</p>
                            <p class="card-date">Date: ${tip.getTips().getDate()}</p>

                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
</div>

<!-- Bootstrap -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>

g