<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Album-List</title>
</head>
<body>
<form method = "post" action = "navigationServlet">
<table>
<c:forEach items = "${requestScope.allAlbums}" var = "currentalbum">
<tr>
	<td><input type = "radio" name = "id" value = "${currentalbum.id}"></td>
	<td>${currentalbum.artist}</td>
	<td>${currentalbum.title}</td>
	<td>${currentalbum.year}</td>
</tr>
</c:forEach>
</table>
<input type = "submit" value = "edit" name = "doThisToAlbum">
<input type = "submit" value = "delete" name = "doThisToAlbum">
<input type = "submit" value = "add" name = "doThisToAlbum">
</form>
</body>
</html>