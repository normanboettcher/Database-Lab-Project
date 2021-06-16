<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Film Anlegen">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="filmAnlegenStyle.css">
<title>Neuen Film anlegen</title>
</head>
<body>
	<!-- Falls Admin nicht eingeloggt, kann Nutzer nicht auf die Seite zugreifen und wird weitergeleitet-->
	<c:if test="${admin == null}">
		<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
	</c:if>
	<!-- Admin wird im Titel begrüßt-->
	<div class="titel">
		<h1>Neuen Film anlegen:</h1>
		<h4>${admin.getVorname()}${admin.getNachname()}in der Rolle als :
			${admin.getRolle()}</h4>
	</div>
	<!-- Form zum anlegen eines Films, Daten werden an das Servlet filmAnlage versendet-->
	<div class="filmAnlage">
		<form method="post" action="FilmAnlegen">
			<table class="table">
				<tr>
					<!-- Input Filmtitel mit Pattern-->
					<td><label class="beschriftung">Filmtitel: </label></td>
					<td><input type="text" placeholder="Filmtitel" class="eingabe"
						name="titel" maxlength="30" required></td>
				</tr>
				<tr>
					<!-- Input Genre mit Pattern-->
					<td><label class="beschriftung">Genre: </label></td>
					<td><select name="genre" class="genre" size="1">
							<option selected>Action</option>
							<option>Horror</option>
							<option>Thriller</option>
							<option>Dokumentation</option>
							<option>Comedy</option>
							<option>Drama</option>
					</select></td>
				</tr>
				<tr>
					<!-- Input Beschreibung mit Pattern-->
					<td><label class="beschriftung">Beschreibung: </label></td>
					<td><textarea wrap="soft" placeholder="Beschreibung"
							class="beschreibung" rows="5" cols="40" maxlength="500"
							name="beschreibung" required></textarea></td>
				</tr>
				<tr>
					<!-- Input Preis mit Pattern-->
					<td><label class="beschriftung">Preis: </label></td>
					<td><input type="text" placeholder="Preis" class="eingabe"
						name="preis" required pattern="[0-9]*[.]?[0-9]+"></td>
				</tr>
				<tr>
					<!-- Input Produzent mit Pattern-->
					<td><label class="beschriftung">Produzent: </label></td>
					<td><input type="text" placeholder="Produzent" class="eingabe"
						name="produzent" maxlength="30" required
						pattern="[A-Za-z\s]{5,30}"></td>
				</tr>
				<tr>
					<!-- Input Datum mit Pattern-->
					<td><label class="beschriftung">Datum: </label></td>
					<td><input type="text" placeholder="DD-MM-YYYY"
						class="eingabe" name="datum" required
						pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}"></td>
				</tr>
				<tr>
					<!-- Input Altersbeschränkung-->
					<td><label class="beschriftung">Altersbeschränkung: </label></td>
					<td><select name="alter" class="alters" size="1">
							<option selected>0</option>
							<option>6</option>
							<option>12</option>
							<option>16</option>
							<option>18</option>
					</select></td>
				</tr>
				<tr>
					<!-- Input YT-Link-->
					<td><label class="beschriftung">Verlinkung: </label></td>
					<td><input type="text" placeholder="Link" class="eingabe"
						name="yt_link"></td>
				</tr>
				<tr>
					<!-- Input Img-->
					<td><label class="beschriftung">Titelbild: </label></td>
					<td><input type="file" class="bild" name="bild" accept="jpg/*"
						required></td>
				</tr>
			</table>
			<button class="button" type="submit">Film speichern</button>
		</form>
		<a href="index.jsp"><button class="button" type="submit">Zurück
				zur Startseite</button></a>
	</div>
</body>
</html>