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
<title>Query Reviews</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
    
	<form action="ReviewsQuery" method="post">
	    <div class="jumbotron">
		<h1>Search for Reviews by Restaurant Id</h1>
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
	<h1>Matching Reviews</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>Id</th>
                <th>UserId</th>
                <th>Stars</th>
                <th>Useful</th>
                <th>Funny</th>
                <th>Cool</th>
                <th>Content</th>
                <th>Date</th>
            </tr></thead>
            <c:forEach items="${reviews}" var="review" >
                <tbody><tr>
                    <td><c:out value="${review.getReviewId()}" /></td>
                    <td><c:out value="${review.getUserId()}" /></td>
                    <td><c:out value="${review.getStars()}" /></td>
                    <td><c:out value="${review.getUseful()}" /></td>
                    <td><c:out value="${review.getFunny()}" /></td>
                    <td><c:out value="${review.getCool()}" /></td>
                    <td><c:out value="${review.getContent()}" /></td>
                    <td><c:out value="${review.getDate()}" /></td>
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
