<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Logout">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="logoutStyle.css">
<title>Goodbye</title>
</head>
<body>
<div class="logout">
	<h3>Goodbye ${name}, hope to see you again!</h3>
	<a href = "login.jsp"><button class="button" type="submit">Erneut einloggen</button></a>
	<a href = "index.jsp"><button class="button" type="submit">Zur Startseite</button></a>
</div>
</body>
</html>