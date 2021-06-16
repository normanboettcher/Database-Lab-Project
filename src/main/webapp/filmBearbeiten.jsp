<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Film Bearbeiten">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="filmBearbeitenStyle.css">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Film Bearbeiten</title>
</head>
<body>
	<!-- Sollte der Nutzer kein Admin sein, wird er weitergeleitet-->
	<c:if test="${admin == null}">
		<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
	</c:if>
	<div class="titel">
		<br>
		<h2>${f.filmtitel()}</h2>
		<br>
	</div>
	<!-- Form mit weiterleitung an FilmAendern Servlett. Die neue Eingabe überschreibt die Aktuelle Eingabe.-->
	<div class="bearbeitung">
		<form action="FilmAendern" method="post">
			<table>
				<tr>
					<th><h3>ID</h3></th>
					<th><h3>Aktuelle Eingabe</h3></th>
					<th><h3>Neue Eingabe</h3></th>
					<th><h3>Verändert</h3></th>
				</tr>
				<tr>
					<!-- Input Img-->
					<td><h4>Titelbild:</h4></td>
					<td><img class="pic" src="${f.getTitelbildQuelle()}"></td>
					<td><input type="file" name="bild_neu" accept="jpg/*"></td>
					<td>${bild_veraendert}</td>
				<tr>
					<!-- Input Filmtitel mit Pattern-->
					<td><h4>Filmtitel:</h4></td>
					<td>${f.filmtitel()}</td>
					<td><input type="text" placeholder="Filmtitel" class="eingabe"
						maxlength="30" pattern="[A-Za-z0-9]{2,30}" name="titel_neu"></td>
					<td>${titel_veraendert}</td>
				<tr>
				<tr>
					<!-- Input Genre-->
					<td><h4>Genre:</h4></td>
					<td>${f.getGenre()}</td>
					<td><select class="genre" name="genre_neu" size="1">
							<option></option>
							<option>Action</option>
							<option>Horror</option>
							<option>Thriller</option>
							<option>Dokumentation</option>
							<option>Comedy</option>
							<option>Drama</option>
					</select></td>
					<td>${genre_veraendert}</td>
				</tr>
				<tr>
					<!-- Input Preis mit Pattern-->
					<td><h4>Preis:</h4></td>
					<td>${f.getPreis()}</td>
					<td><input class="preis" type="number" name="preis_neu"
						pattern="[0-9]*[.]?[0-9]+"></td>
					<td>${preis_veraendert}</td>
				</tr>
				<tr>
					<!-- Input Beschreibung mit Pattern-->
					<td><h4>Beschreibung:</h4></td>
					<td>${f.printBeschreibung()}</td>
					<td><textarea class="beschreibung" placeholder="Beschreibung"
							rows="5" cols="40" maxlength="500" name="beschr_neu"></textarea></td>
					<td>${beschr_veraendert}</td>
				</tr>
				<tr>
					<!-- Input Altersbeschränkung-->
					<td><h4>Altersbeschränkung:</h4></td>
					<td>${f.getAltersbeschraenkung()}</td>
					<td><input class="alters" type="number" name="alter_neu"
						step="6" max="18" min="0"></td>
					<td>${alter_veraendert}</td>
				</tr>
				<tr>
					<!-- Input YT-Link-->
					<td><h4>Youtube-Quelle:</h4></td>
					<td>${f.getYoutubeLink()}</td>
					<td><p>Die Bildquelle ist auch Rechtlichen Gründen nur vom Management zu ändern.</p>p><br>
					<td>${yt_link_veraendert}</td>
				</tr>
				<tr>
					<!-- Input Produzent mit Pattern-->
					<td><h4>Produzent:</h4></td>
					<td>${f.getProduzent().ganzerNameProduzent()}</td>
					<td><input type="text" name="produzent_neu"
						placeholder="Produzent" class="eingabe" maxlength="30"
						pattern="[A-Za-z\s]{5,30}"></td>
					<td>${produzent_veraendert}</td>
				</tr>
				<tr>
					<!-- Input Veröffentlichung mit Pattern-->
					<td><h4>Veröffentlicht:</h4></td>
					<td>${f.veroeffentlichtAm()}
					<td><input type="text" name="datum_neu"
						placeholder="DD-MM-YYYY" class="eingabe"
						pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}"></td>
					<td>${datum_veraendert}</td>
				</tr>
			</table>
			<!-- Sollten änderungen vorgenommen werden, wird die Anzahl der änderungen für den Film angezeigt-->
			<input type="hidden" name="film" value="${f}">
			<button class="button" type="submit">Änderungen bestätigen</button>
			<c:if test="${counter_aenderungen > 0}">
				<p>${counter_aenderungen}Änderungen wurde für ${f.filmtitel()}
					übernommen</p>
			</c:if>
			<!-- Gibt an wann Zuletzt etwas geändert wurde-->
			<p>Zuletzt geändert von: ${aenderung_admin}
				(${counter_aenderungen} Änderungen)</p>
		</form>
		<a href="filme.jsp"><button class="button" type="submit">Zurück</button></a>
	</div>
</body>
</html>