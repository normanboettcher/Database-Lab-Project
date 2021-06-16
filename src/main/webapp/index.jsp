<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Startseite">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- Costum Style -->
<link rel="stylesheet" href="indexStyle.css">
<link rel="stylesheet" href="navStyle.css">
<!-- Font Style for Icons -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>HOMESTAR - Watch Movies And Tv Shows Online</title>
</head>
<body>
	<embed src="../musik/startsound.mp3" autostart="true" loop="false"
		hidden="true" height="0" width="0">
		<!-- Navbar mit den einzelnen Seiten -->
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
	<div class="afterLogin">
		<!-- Sobald Admin eingeloggt ist, wird er mit Namen begrüßt und kann Funktion durchführen   -->
		<c:if test="${admin != null }">
			<p>Welcome to HOMESTAR, ${admin.getVorname()}
				${admin.getNachname()} (${admin.getRolle()})</p>
			<a href="filmAnlegen.jsp"><button class="regButton" type="submit">Neuen
					Film hinzufügen</button></a>
			<a href="KundenAnzeigen"><button class="regButton" type="submit">Alle
					Kunden anzeigen</button></a>
			<a href="UserAnzeigen"><button class="regButton" type="submit">Alle
					User anzeigen</button></a>
			<a href="RechnungenAnzeigen"><button class="regButton"
					type="submit">Alle Rechnungen anzeigen</button></a>
			<a href="KontoBearbeiten"><button class="regButton" type="submit">Mein
					Konto bearbeiten</button></a>
		</c:if>
	</div>
	<!-- Falls kein Nutzer eingeloggt ist, kann dieser sich auf der Index seite Registrieren  -->
	<div class="start">
		<c:if test="${kunde == null && admin == null }">
			<p>Welcome to HOMESTAR!</p>
			<p>We're the best online video store in the world!</p>
			<p>Join us now!</p>
			<a href="createAccount.jsp"><button class="regButton"
					type="submit">Registrieren</button></a>
		</c:if>
	</div>
	<!-- Footer mit verlinkung zum Impressum-->
	<div class="footer">
		<p class="footerIn">
			&copy; 2020 HOMESTAR <a class="footerIn" href="impressum.jsp">IMPRESSUM</a>
		</p>
	</div>
</body>
</html>
