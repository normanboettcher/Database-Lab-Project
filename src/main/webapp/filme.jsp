<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Alle Filme">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="filmeStyle.css">
<link rel="stylesheet" href="navStyle.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>HOMESTAR - Watch Movies And Tv Shows Online</title>
</head>
<body>
	<div class="navbar">
		<a href="index.jsp" class="logo">HOMESTAR</a>
		<ul class="nav">
			<li><a href="index.jsp" class="men"><i class="fa fa-home"></i> Home</a>
			<li><a href="FilmServlet" class="men"><i class="fa fa-film"></i>
					Filme</a></li>
			<!-- Wenn Kunde eingeloggt ist wird "Mein Konto" angezeigt -->
			<c:if test="${kunde != null}">
				<li><a href="Kundenkonto" class="men"><i class="fa fa-user"></i>
						Mein Konto</a></li>
			</c:if>
			<li><a href="WarenkorbAnzeigen" class="men"><i
					class="fa fa-shopping-cart"></i> Warenkorb</a> <!-- Falls Nutzer nicht angemeldet ist, wird Login angezeigt. Nutzer kann sich einloggen -->
				<c:if test="${kunde == null && admin == null }">
					<li><a href="login.jsp" class="men"><i class="fa fa-lock"></i>
							Login</a></li>
					<!-- Sobald Nutzer eingeloggt ist, wird Logout angezeigt, Nutzer kann sich ausloggen -->
				</c:if> <c:if test="${kunde != null || admin != null }">
					<li><a href="LogoutServlet" class="men"><i
							class="fa fa-unlock-alt"></i> Logout</a></li>
					<!-- Admin kann nicht auf Kontakt zugreifen, wird nur bei Nutzern ohne Rolle Admin angezeigt  -->
				</c:if> <c:if test="${admin == null || admin != null}">
					<li><a href="kontakt.jsp" class="men"><i
							class="fa fa-phone"></i> Kontakt</a></li>
				</c:if>
		</ul>
	</div>
	<!-- Zeigt alle Filme an-->
	<div class="container">
		<c:forEach items="${filmliste}" var="film">
			<div class="img">
				<br>
				<img class="pic" alt="picture" src="${film.getTitelbildQuelle()}">
				<span class="titel">${film.filmtitel()}</span> <span>${film.getPreis()}
					&#8364</span><br>
				<!-- Film in den Warenkorb einfügen-->
				<!-- Sollte Kunde nicht eingeloggt sein, können die Artikel nicht in den Warenkorb eingefügt werden-->
				<c:if test="${kunde != null }">
					<form action="WarenkorbServlet" method="post">
						<input type="hidden" value="${film.getId()}" name="film_id">
						<a class="add-cart cart">
							<button class="button" type="submit">Zum Warenkorb hinzufügen</button>
						</a>
					</form>
				</c:if>
				<form action="FilmDetails" method="post">
					<input type="hidden" name="film_id" value="${film.getId()}">
					<button class="Bbutton" type="submit">Filmdetails</button>
					<br>
				</form>
				<!-- Weiterleitung zu Film bearbeiten und Löschen, falls Nutzer Admin ist.-->
				<c:if test="${admin != null }">
					<form method="post" action="FilmBearbeiten">
						<input type="hidden" name="film_id" value="${film.getId()}">
						<button class="Bbutton" type="submit">Film Bearbeiten</button>
						<br>
					</form>
					<form method="post" action="FilmLoeschen">
						<input type="hidden" name="film_id" value="${film.getId()}">
						<button class="Bbutton" type="submit">Film löschen</button>
					</form>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<br>
</body>
</html>