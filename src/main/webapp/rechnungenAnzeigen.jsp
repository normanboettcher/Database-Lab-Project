<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Alle Rechnungen">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="rechnungAnzeigenStyle.css">
<title>Rechnungen</title>
</head>
<body>
<!--Nur der Admin kann auf diese Seite zugreifen-->
<c:if test="${admin == null}">
<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
</c:if>
	<br><h1>Rechnungen</h1><br>
	<div class="meldung">
		<p>${meldung}</p>
	</div>
	<!--Zeigt Tabelle mit allen Rechnungen an-->
	<div class="rechnungen">
		<table>
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th>Vorname</th>
				<th>Nachname</th>
				<th>Geburtsdatum</th>
				<th>Strasse</th>
				<th>Hausnummer</th>
				<th>Postleitzahl</th>
				<th>Ort</th>
				<th>Betrag</th>
				<th>Bezahlstatus</th>
				<th>Zahlmethode</th>
				<th>Zahlungsfrist</th>
				<th>Rechnungsdatum</th>
				<th>Artikel</th>
				<th>Speicherort</th>
				<th>Rechnung löschen</th>
			</tr>
			<c:forEach items="${rechnungen}" var="r">
			<tr>
				<td>${r.getRechnungsID()}</td>
				<td>${r.getEmpfaenger().getEmail()}</td>
				<td>${r.getEmpfaenger().getVorname()}</td>
				<td>${r.getEmpfaenger().getNachname()}</td>
				<td>${r.getEmpfaenger().getGeburtsdatum()}</td>
				<td>${r.getEmpfaenger().getAdresse().getStrasse()}</td>
				<td>${r.getEmpfaenger().getAdresse().getHausnummer()}</td>
				<td>${r.getEmpfaenger().getAdresse().getPLZ()}</td>
				<td>${r.getEmpfaenger().getAdresse().getOrt()}</td>
				<td>${r.getGesamtBetragRechnung()} EUR</td>
				<td>${r.getBezahlstatus()}</td>
				<td>${r.getEmpfaenger().getZahlmethode().getZahlmethodeBezeichnung()}</td>
				<td>${r.getZahlungsfrist()}</td>
				<td>${r.getRechnungsdatum()}</td>
				<td>${r.getProdukte()}</td>
				<td>${r.getPfad()}</td>
				<td><form method="post" action="RechnungLoeschen">
					<input type="hidden" name="rechnung" value="${r.getRechnungsID()}">
						<a href="#"><button class="button" type="submit">Löschen</button></a>
					</form>
				</td>
			</tr>
			</c:forEach>
		</table>	
		<a href="index.jsp"><button class="button" type="submit">Zurück zur Startseite</button></a>
	</div>

</body>
</html>