<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findyelpusers" method="post">
		<h1>Search for a Yelp User by User ID</h1>
		<p>
			<label for="userId">User ID</label>
			<input id="userIde" name="userId" value="${fn:escapeXml(param.userId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="yelpUserCreate"><a href="yelpusercreate">Create YelpUser</a></div>
	<br/>
	<h1>Matching Yelp Users</h1>
        <table border="1">
            <tr>
                <th>User ID</th>
                <th>User Name</th>
                <th>Review Count</th>
                <th>Delete BlogUser</th>
                <th>Update BlogUser</th>
            </tr>
            <c:forEach items="${yelpUsers}" var="yelpUser" >
                <tr>
                    <td><c:out value="${yelpUser.getUserId()}" /></td>
                    <td><c:out value="${yelpUser.getUserName()}" /></td>
                    <td><c:out value="${yelpUser.getReviewCount()}" /></td>
                   <%--  <td><a href="userblogposts?username=<c:out value="${blogUser.getUserName()}"/>">BlogPosts</a></td>
                    <td><a href="blogcomments?username=<c:out value="${blogUser.getUserName()}"/>">BlogComments</a></td> --%>
                    <td><a href="./yelpuserdelete?username=<c:out value="${yelpUser.getUserId()}"/>">Delete</a></td>
                    <td><a href="./yelpuserupdate?username=<c:out value="${yelpUser.getUserId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
