<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Mein Konto">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="kundenkontoBearbeitenStyle.css">
<title>Konto bearbeiten</title>
</head>
<body>
<!-- Falls Nutzer nicht eingeloggt ist wird dieser weitergeleitet-->
<c:if test="${admin == null && kunde == null}">
<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
</c:if>
	<div class="titel">
		<br>
		<!-- Wenn der Kunde eingeloggt ist, bearbeitet er sein Profil selbst-->
		<c:if test="${alsAdminBearbeiten.equals('nein')}">
			<h2>Hallo, ${user.getVorname()}
				${user.getNachname()} was möchten Sie ändern?</h2>
		</c:if>
		<!-- Admin kann das Konto vom Kunden bearbeiten-->
		<c:if test="${alsAdminBearbeiten.equals('ja')}">
			<h2>Kundenkonto von ${user.getVorname()} ${user.getNachname()}
				als ${admin.getVorname()} ${admin.getNachname()} bearbeiten</h2>
		</c:if>
		<br>
	</div>
	<!-- Zeigt Tabelle mit den aktuellen Werten und den Neuen. Neue Werte überschreiben Alte.-->
	<div class="bearbeitung">
		<form action="AenderungBestaetigen" method="post">
			<table>
				<tr>
					<th><h3>ID</h3></th>
					<th><h3>Aktuelle Eingabe</h3></th>
					<th><h3>Neue Eingabe</h3></th>
					<th></th>
				</tr>
				<tr>
				<!-- Vorname mit Pattern-->
					<td><h4>Vorname:</h4></td>
					<td>${user.getVorname()}</td>
					<td><input type="text" class="eingabe" name="vorname_neu" placeholder="Eingabe"
						pattern="[A-Za-zÖöÜüÄä]{2,50}"></td>
					<td>${vorname_geaendert}</td>
				</tr>
				<tr>
				<!-- Nachname mit Pattern-->
					<td><h4>Nachname:</h4></td>
					<td>${user.getNachname()}</td>
					<td><input type="text" class="eingabe"  name="nachname_neu" placeholder="Eingabe"
						pattern="[A-Za-zÖöÜüÄä]{2,50}"></td>
					<td>${nachname_geaendert}</td>
				</tr>
				<tr>
				<!-- Geburstag kann nicht geändert werden-->
					<td><h4>Geburtstag:</h4></td>
					<td>${user.getGeburtsdatum()}</td>
					<td>Der Geburtstag ist nicht editierbar.</td>
					<td></td>
				</tr>
				<tr>
				<!-- Straße mit Pattern-->
					<td><h4>Strasse:</h4></td>
					<td>${user.getAdresse().getStrasse()}</td>
					<td><input type="text" class="eingabe" name="strasse_neu" placeholder="Eingabe"
						pattern="[A-ZÖÄÜ]{1,1}[A-ZÖÄÜa-zöäü\s]{1,49}"></td>
					<td>${strasse_geaendert}</td>
				</tr>
				<tr>
				<!-- Hausnummer mit Pattern-->
					<td><h4>Hausummer:</h4></td>
					<td>${user.getAdresse().getHausnummer()}</td>
					<td><input type="text" class="eingabe" name="hausnummer_neu" placeholder="Eingabe"
						pattern="[1-9]{1,1}[\d|a-z]{0,1}[\d|a-z]{0,1}[a-z]{0,1}"></td>
					<td>${hausnummer_geaendert}</td>
				</tr>
				<tr>
				<!-- Plz mit Pattern-->
					<td><h4>Postleitzahl:</h4></td>
					<td>${user.getAdresse().getPLZ()}</td>
					<td><input type="text" class="eingabe" name="plz_neu" placeholder="Eingabe"
						pattern="[1-9]{1,1}[\d]{4,4}"></td>
					<td>${plz_geaendert}</td>
				</tr>
				<tr>
				<!-- Ort mit pattern-->
					<td><h4>Ort:</h4></td>
					<td>${user.getAdresse().getOrt()}</td>
					<td><input type="text" class="eingabe" name="ort_neu" pattern="[\D][\w]{2,50}" placeholder="Eingabe"></td>
					<td>${ort_geaendert}</td>
				</tr>
				<tr>
				<!-- EMail mit Pattern-->
					<td><h4>E-Mail:</h4></td>
					<td>${user.getEmail()}</td>
					<td>Die E-Mail Adresse ist nicht editierbar.</td>
					<td></td>
					<c:if test="${alsAdminBearbeiten.equals('nein')}">
						<input type="hidden" name="userBearbeitet">
						<tr>
						<!--Passwort kann nicht geändert werden-->
							<td><h4>Passwort:</h4></td>
							<td>Altes Passwort:<input class="eingabe" type="password" name="pw_alt" placeholder="Eingabe"
								min="8" max="50" pattern=^([A-Za-z0-9ÄÖÜäöüß]{8,50})$><br>
								Neues Passwort:<input class="eingabe" type="password" name="pw1_neu" placeholder="Eingabe" min="8"
								max="50" pattern=^([A-Za-z0-9ÄÖÜäöüß]{8,50})$><br>
								Passwort-Wiederholung:<input class="eingabe" type="password" name="pw2_neu" placeholder="Eingabe"
								min="8" max="50" pattern=^([A-Za-z0-9ÄÖÜäöüß]{8,50})$><br>
							</td>
							<td>${pw_geaendert}</td>
						</tr>
					</c:if>
			</table>
			<input type="hidden" name="kunden_email" value="${kunden_email}">
			<button class="button" type="submit">Änderungen bestätigen</button>
		</form>
		<a href="index.jsp"><button class="button" type="submit">Zurück
				zur Startseite</button></a>
	</div>
</body>
</html>