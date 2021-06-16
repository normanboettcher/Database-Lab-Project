<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Registrierung">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="createAccountStyle.css">
<title>Registrierungsformular</title>
</head>
<body>
	<!-- Falls Admin oder Kunde auf die Seite zugreifen werden diese weitergeleitet-->
	<c:if test="${admin != null || kunde != null}">
		<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
	</c:if>
	<!-- Formular zum Registrieren-->
	<div class="regform">
		<h1>Registrierungsformular</h1>
	</div>
	<div class="main">
		<form method="post" action="HandleCreateAccountServlet">
			<div id="person">
				<!-- Input Name mit Pattern-->
				<h2 class="abschnitt">Name</h2>
				<input class="vorname" type="text" name="vor_name"
					placeholder="Vorname" maxlength="50" required
					pattern="[A-Za-zÖöÜüÄä]{2,50}"> <input class="nachname"
					type="text" name="nach_name" placeholder="Nachname" maxlength="50"
					required pattern="[A-Za-zÖöÜüÄä]{2,50}"><br> <input
					class="geburtstag" type="text" name="geb_tag"
					placeholder="Geburtsdatum">
			</div>
			<!-- Input Anschrift mit Pattern-->
			<h2 class="abschnitt">Anschrift</h2>
			<input class="strasse" type="text" name="strasse"
				placeholder="Straße" maxlength="50" required
				pattern="[A-ZÖÄÜ]{1,1}[A-ZÖÄÜa-zöäü\s]{1,49}"> <input
				class="nr" type="text" name="nr" placeholder="Straßen Nr."
				maxlength="4" required
				pattern="[1-9]{1,1}[\d|a-z]{0,1}[\d|a-z]{0,1}[a-z]{0,1}"><br>
			<input class="plz" type="text" name="plz" placeholder="Postleitzahl"
				maxlength="5" required pattern="[1-9]{1,1}[\d]{4,4}"> <input
				class="stadt" type="text" name="stadt" placeholder="Stadt"
				maxlength="50" required pattern="[\D][\w]{2,50}">
			<!-- Input EMAIL und Passwort mit Pattern-->
			<h2 class="abschnitt">Account</h2>
			<input class="email" type="text" name="email"
				placeholder="E-Mail Adresse" maxlength="70" required
				pattern="^[A-Za-z0-9\.\+_-]+@[A-Za-z0-9\._-]+\.[a-uA-Z]+$"><br>
			<input class="passwort" type="password" name="passwort"
				placeholder="Passwort" maxlength="50" required
				pattern=^([A-Za-z0-9ÄÖÜäöüß]{8,50})$> <input
				class="passwort2" type="password" name="passwort2"
				placeholder="Passwort bestätigen" maxlength="50" required
				pattern=^([A-Za-z0-9ÄÖÜäöüß]{8,50})$><br>
			<button class="regButton" type="submit">Registrieren</button>
		</form>
		<!-- Button zurück zur Startseite-->
		<a href="index.jsp"><button class="regButton" type="submit">Zurück
				zur Startseite</button></a><br>
	</div>

</body>

</html>