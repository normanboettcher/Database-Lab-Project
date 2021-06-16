<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Verweigerte Aktion">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Registrierung fehlgeschlagen</title>
</head>
<body>
	<div>
		<br>Leider sind folgende Fehler bei der Registrierung aufgetreten:<br>
		<br>${fehlermeldung_email}<br>
		<br>${fehlermeldung_passwort}<br>
		<a href = "createAccount.jsp">Jetzt erneut versuchen</a>
	</div>
</body>
</html>