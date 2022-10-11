<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action = "createNewListServlet" method="post">
List Name: <input type="text" name = "listName"><br />
Trip Date: <input type="text" name = "month" placeholder="mm" size="4">
<input type="text" name = "day" placeholder="dd" size="4">
<input type="text" name = "year" placeholder="yyyy" size="4">
Customer Name: <input type="text" name = "customerName"><br />

Available Albums:<br />
<select name = "allAlbumsToAdd" multiple size = "6">
<c:forEach items = "${requestScope.allAlbums}" var="currentalbum">
<option value = "${currentalbum.id}">${currentalbum.artist} | ${currentalbum.title} | ${currentalbum.year}</option>
</c:forEach>
</select>
<br />
<input type="submit" value="Create list and Add Albums">
</form>
<a href = "index.html">Go add new albums instead</a>
</body>
</html>