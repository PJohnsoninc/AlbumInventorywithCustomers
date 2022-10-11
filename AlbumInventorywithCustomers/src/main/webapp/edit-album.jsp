<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit-Album</title>
</head>
<body>
<form action = "editAlbumServlet" method = "post">
Artist: <input type = "text" name = "artist" value = "${albumToEdit.artist}">
Title: <input type = "text" name = "title" value = "${albumToEdit.title}">
Year: <input type = "text" name = "year" value = "${albumToEdit.year}">
<input type = "hidden" name = "id" value = "${albumToEdit.id}">
<input type = "submit" value = "Save Edited Album">
</form>
</body>
</html>