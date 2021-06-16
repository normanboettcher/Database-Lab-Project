
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Startseite">
<meta name="author" content="Thomas Mook">
<link rel="stylesheet" href="warenkorbStyle.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Warenkorb</title>
</head>
<body>
	<!--Nur eingeloggte Nutzer können auf den Warenkorb zugreifen-->
	<c:if test="${admin == null && kunde == null}">
		<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
	</c:if>
	<div class="titel">
		<!--Die Funktion begrüßt den Nutzer-->
		<h2>Ihr aktueller Warenkorb, ${kunde.getVorname()}
			${kunde.getNachname()}</h2>
	</div>
	<!--Wenn der Warenkorb leer ist wird eine Meldung ausgegeben mit dem Button der den Nutzer auf die Filmliste weiterleitet-->
	<div class="warenkorb">
		<c:if test="${warenkorb.zeigeVollenWarenkorb().size() == 0}">
			<p>Ihr Warenkorb hat noch keinen Inhalt...
			<p>
			<form action="FilmServlet" method="get">
				<button class="button" type="submit">Jetzt shoppen und
					Filme schauen...</button>
			</form>
		</c:if>
		<!--Wenn Warenkorb Artikel enthält werden diese Aufgelistet als Tabelle-->
		<c:if test="${warenkorb.zeigeVollenWarenkorb().size() > 0}">
			<table>
				<tr>
					<th><h3>Artikel</h3></th>
					<th><h3>Preis</h3></th>
				</tr>
				<c:forEach items="${warenkorb.zeigeVollenWarenkorb()}" var="w">
					<tr>
						<td>${w.filmtitel()}</td>
						<td>${w.getPreis()}</td>
					</tr>
				</c:forEach>
				<tr>
					<td><h3>Gesamtpreis</h3></td>
					<td>${warenkorb.getGesamtpreis()}</td>
				</tr>
			</table>
			<!--Zeigt die Bezahlmethoden-->
			<form action="Bestellen" method="post">
				<input type="hidden" name="warenkorb" value="${warenkorb}">
				<input type="radio" name="zahlmethode" value="paypal">PayPal
				<input type="radio" name="zahlmethode" value="kredit">Kreditkarte
				<input type="radio" name="zahlmethode" value="klarna">Klarna
				<!--Solange nicht bestellt wurde, wird der Button Bestellen angezeigt-->
				<c:if test="${bestellt != true}">
					<button type="submit" name="bestellen" class="button">Jetzt
						bestellen</button>
				</c:if>
			</form>
		</c:if>
		<a href="index.jsp"><button type="submit" class="button">Zurück
				zur Startseite</button></a>
		<!--Nach der Bestellung wird eine Info ausgegeben-->
		<c:if test="${bestellt == true}">
			<p>${meldung}</p>
		</c:if>
	</div>
</body>
</html>