<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Kundenkonto">
<meta name="author" content="Thomas Mook">
<link rel="stylesheet" href="kundenkontoStyle.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Mein Konto</title>
</head>
<body>
<!-- Seite kann nur angezeigt werden, falls Nutzer eingeloggt ist-->
<c:if test="${kunde == null}">
<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
</c:if>
<!-- Zeigt das Kundenkonto mit allen Attributen an-->
	<div class="titel">
	<br><h2>Ihre Kontoinformationen, ${name}</h2><br>
	</div>
	<div class="kontoinfo">
		<table>
			<tr>
				<td><h3>Vorname:</h3></td>
				<td>${kunde.getVorname()}</td>
			</tr>
			<tr>
				<td><h3>Nachname:</h3></td>
				<td>${kunde.getNachname()}</td>
			</tr>
			<tr>
				<td><h3>Geburtstag:</h3></td>
				<td>${kunde.getGeburtsdatum()}</td>
			</tr>
			<tr>
				<td><h3>Email:</h3></td>
				<td>${kunde.getEmail()}</td>
			</tr>
			<tr>
				<td><h3>Rechnungsanschrift:</h3></td>
				<td>${kunde.getAdresse().getStrasse()} ${kunde.getAdresse().getHausnummer()} <br>
					${kunde.getAdresse().getPLZ()} <br> ${kunde.getAdresse().getOrt()}
				</td>
			</tr>
		</table>
		</div>
		<!-- Zeigt alle Bestellungen vom Kunden an-->
		<div class="bestellungen">
		<br><br><h2>Bestellungen:</h2><br>
		<table>
			<tr>
				<th><h3>Rechnungsnummer</h3></th>
				<th><h3>Artikel</h3></th>
				<th><h3>Betrag</h3></th>
				<th><h3>Zahlmethode</h3></th>
				<th><h3>Frist</h3></th>
				<th><h3>Status</h3></th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items = "${rechnungen}" var = "r">
				<tr>
					<td>${r.getRechnungsID()}</td>
					<td>${r.getProdukte()}</td>
					<td>${r.getGesamtBetragRechnung()}</td>
					<td>${r.getEmpfaenger().getZahlmethode().getZahlmethodeBezeichnung()}</td>
					<td>${r.getEmpfaenger().getZahlmethode().getZahlungsfrist()}</td>
					<td>${r.getBezahlstatus()}</td>
					<td>
					<!-- Sollte eine Bestllung nicht bezahlt sein, kann diese bezahlt werden-->
						<c:if test = "${r.getBezahlstatus().equals('offen')}">
							<form action = "Bezahlen" method = "post">
								<input type = "hidden" name = "rechnungs_id" value = "${r.getRechnungsID()}">
								<button class="button2" type = "submit">Bezahlen</button>
							</form>
						</c:if>
					</td>
					<td>
					<!-- Eine Rechnung kann Heruntergeladen werden-->
						<form action = "Herunterladen" method = "post">
							<input type = "hidden" name = "rechnung" value = "${r.getRechnungsID()}"> 
							<button class="button2" type = "submit">Als PDF herunterladen</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<a href="KontoBearbeiten"><button class="button" type="submit">Mein Konto bearbeiten</button></a>		
		<a href = "index.jsp"><button class="button" type="submit">Zurück zur Startseite</button></a>
		</div>	
</body>
</html>