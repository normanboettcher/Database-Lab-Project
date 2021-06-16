<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Userliste">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="userAnzeigenStyle.css">
<title>Alle User</title>
</head>
<body>
<!--Nur der Admin kann auf diese Seite zugreifen-->
	<c:if test="${admin == null}">
		<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
	</c:if>
	<br>
	<!--Liste mit allen Usern-->
	<h1>User</h1>
	<br>
	<div class="user">
		<table>
			<tr>
				<th>Email</th>
				<th>Passwort</th>
				<th>Vorname</th>
				<th>Nachname</th>
				<th>Geburtsdatum</th>
				<th>Strasse</th>
				<th>Hausnummer</th>
				<th>Postleitzahl</th>
				<th>Ort</th>
				<th>Rolle</th>
			</tr>
			<c:forEach items="${userliste}" var="user">
				<tr>
					<td>${user.getEmail()}</td>
					<td>${user.getPasswort()}</td>
					<td>${user.getVorname()}</td>
					<td>${user.getNachname()}</td>
					<td>${user.getGeburtsdatum()}</td>
					<td>${user.getAdresse().getStrasse()}</td>
					<td>${user.getAdresse().getHausnummer()}</td>
					<td>${user.getAdresse().getPLZ()}</td>
					<td>${user.getAdresse().getOrt()}</td>
					<td>${user.getRolle()}</td>
				</tr>
			</c:forEach>
		</table>
		<a href="index.jsp"><button class="button" type="submit">Zurück
				zur Startseite</button></a>
	</div>
</body>
</html>