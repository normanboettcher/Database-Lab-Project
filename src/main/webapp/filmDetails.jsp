<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Details zum Film">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="filmDetailsStyle.css">
<title>Filmdetails</title>
</head>
<body>
	<div class="movieImage">
		<br>
		<h1>${film.filmtitel()}</h1>
		<br> <img class="pic" alt="." src="${film.getTitelbildQuelle()}">
	</div>
	<div class="showDetails">
		<!-- Tabelle mit den Filmdetails-->
		<table class="filmdetails">
			<tr>
				<th>Filmtitel</th>
				<th>Genre</th>
				<th>Altersbeschränkung</th>
				<th>Preis</th>
				<th>Bewertung</th>
				<th>Produzent</th>
			</tr>
			<tr>
				<td>${film.filmtitel()}</td>
				<td>${film.getGenre()}</td>
				<td>${film.getAltersbeschraenkung()}</td>
				<td>${film.getPreis()}&#8364</td>
				<td>${film.getBewertung()}</td>
				<td>${film.getProduzent().ganzerNameProduzent()}</td>
			</tr>
			<tr>
				<td colspan="6">${film.printBeschreibung()}</td>
		</table>
		<br>
		<!-- YT-Link eingebettet-->
		<h4 class="link">Der Trailer zum Film "${film.filmtitel()}"!</h4>
		<p class="link">${film.getYoutubeLink()}</p>
		<br>
		<!-- Admin kann einen Film nicht bewerten, Kunde der nicht eingeloggt ist, wird weitergeleitet.
		Nur ein eingeloggter Kunde kann einen Film bewerten. Die Bewertung wird in die Gesamtbewertung miteinberechnet.-->
		<div class="bewertung">
			<c:if test="${admin == null}">
				<h4>Bewerten Sie jetzt "${film.filmtitel()}"!</h4>
				<br>
				<form action="FilmBewerten" method="post">
					<input type="hidden" name="film" value="${film}">
					<table>
						<tr>
							<th><input type="radio" name="bewertung_abgabe" value="1.0">1
								Stern</th>
							<th><input type="radio" name="bewertung_abgabe" value="1.5">1.5
								Sterne</th>
							<th><input type="radio" name="bewertung_abgabe" value="2.0">2
								Sterne</th>
							<th><input type="radio" name="bewertung_abgabe" value="2.5">2.5
								Sterne</th>
							<th><input type="radio" name="bewertung_abgabe" value="3.0">3
								Sterne</th>
							<th><input type="radio" name="bewertung_abgabe" value="3.5">3.5
								Sterne</th>
							<th><input type="radio" name="bewertung_abgabe" value="4.0">4
								Sterne</th>
							<th><input type="radio" name="bewertung_abgabe" value="4.5">4.5
								Sterne</th>
							<th><input type="radio" name="bewertung_abgabe" value="5.0">5
								Sterne</th>
						</tr>
					</table>

					<button class="button" type="submit">Bewertung abgeben</button>
				</form>
			</c:if>
		</div>
		<!-- Nur ein eingloggter Kunde kann den Film zum Warenkorb hinzufügen.-->
		<c:if test="${kunde != null }">
			<form action="WarenkorbServlet" method="post">
				<input type="hidden" value="${film.getId()}" name="film_id">
				<a class="add-cart cart"><button class="button" type="submit">Zum
						Warenkorb hinzufügen</button></a>
			</form>
		</c:if>
		<a href="filme.jsp"><button class="button" type="submit">Zurück
				zu den Filmen</button></a>
	</div>
</body>
</html>