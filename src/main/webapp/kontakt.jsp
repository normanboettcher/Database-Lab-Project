<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Kontakt">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="kontaktStyle.css">
<title>Kontakt</title>
</head>
<body>
<!--Admin hat kein Zugriff auf Kontaktaufnahme, da nicht nötig -->
	<c:if test="${admin != null}">
		<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
	</c:if>
	<div class="titel">
		<br>
		<h2>Hallo ${user.getVorname()} ${user.getNachname()}, wie können
			wir helfen?</h2>
	</div>
	<div class="kontakt">
		<form action="KontaktForm" method="post">
			<table>
				<tr>
					<td><h3>Vorname:</h3></td>
					<td><input type="text" name="vorname" class="eingabe"
						placeholder="Vorname" maxlength="50" required
						pattern="[A-Za-zÖöÜüÄä]{2,50}"></td>
				</tr>
				<tr>
					<td><h3>Nachname:</h3></td>
					<td><input type="text" name="nachname" class="eingabe"
						placeholder="Nachname" maxlength="50" required
						pattern="[A-Za-zÖöÜüÄä]{2,50}"></td>
				</tr>
				<tr>
					<td><h3>E-Mail:</h3></td>
					<td><input type="text" class="eingabe" name="eingabe"
						placeholder="E-Mail Adresse" maxlength="70" required
						pattern="^[A-Za-z0-9\.\+_-]+@[A-Za-z0-9\._-]+\.[a-uA-Z]+$"></td>
				</tr>
				<tr>
					<td><h3>Kommentar:</h3></td>
					<td><textarea class="beschreibung" placeholder="Beschreibung"
							rows="5" cols="40" maxlength="500" required></textarea></td>
				</tr>
			</table>
			<button class="button" type="submit">Abschicken</button>
		</form>
		<a href="index.jsp"><button class="button" type="submit">Zurück
				zur Startseite</button></a>
	</div>
</body>
</html>