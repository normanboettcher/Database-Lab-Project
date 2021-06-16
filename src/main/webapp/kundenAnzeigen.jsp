<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Verweigerte Aktion">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="kundenAnzeigenStyle.css">
<title>Alle Kunden</title>
</head>
<body>
	<!-- Seite kann nur angezeigt werden, wenn Nutzer admin ist-->
	<c:if test="${admin == null}">
		<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
	</c:if>
	<!-- Zeigt alle Kunden in einer Liste an-->
	<br>
	<h1>Kunden</h1>
	<br>
	<div class="container">
		<table class="kunden">
			<tr>
				<th>Email</th>
				<th>Vorname</th>
				<th>Nachname</th>
				<th>Geburtsdatum</th>
				<th>Strasse</th>
				<th>Hausnummer</th>
				<th>Postleitzahl</th>
				<th>Ort</th>
				<th>Offene Rechnugen</th>
				<th>Kunden bearbeiten</th>
				<th>Kunden löschen</th>
			</tr>
			<c:forEach items="${kundenliste}" var="kunde">
				<tr>
					<td>${kunde.getEmail()}</td>
					<td>${kunde.getVorname()}</td>
					<td>${kunde.getNachname()}</td>
					<td>${kunde.getGeburtsdatum()}</td>
					<td>${kunde.getAdresse().getStrasse()}</td>
					<td>${kunde.getAdresse().getHausnummer()}</td>
					<td>${kunde.getAdresse().getPLZ()}</td>
					<td>${kunde.getAdresse().getOrt()}</td>
					<td>${kunde.getOffenerBetrag()}EUR</td>
					<!-- Ebenfalls kann der Admin die Kundenkonten bearbeiten, sowie einen Kunden löschen-->
					<td><form action="KontoBearbeiten" method="get">
							<input type="hidden" name="kunden_email"
								value="${kunde.getEmail()}"> <input type="hidden"
								name="alsAdminBearbeiten" value="ja"> <a href="#"><button
									class="button" type="submit">Bearbeiten</button></a>
						</form></td>
					<td><form action="KundenLoeschen" method="post">
							<input type="hidden" name="email" value="${kunde.getEmail()}">
							<a href="#"><button class="button" type="submit">Löschen</button></a>
						</form></td>
				</tr>
			</c:forEach>
		</table>
		<a href="index.jsp"><button class="button" type="submit">Zurück
				zur Startseite</button></a>
	</div>
</body>
</html>