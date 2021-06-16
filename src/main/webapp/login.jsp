<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Verweigerte Aktion">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="loginStyle.css">
<link rel="stylesheet" href="navStyle.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Login</title>
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
<!-- Eingeloggte Nutzer können nicht drauf zugreifen-->
<c:if test="${admin != null || kunde != null}">
<meta http-equiv="refresh" content="0; URL=aktionVerweigert.jsp">
</c:if>
<!-- Login Bereich-->
	<form class="box" action="LoginServlet" method="post">
	<h1>Login</h1>
	<input type="text" name="username" placeholder="Username">
	<input type="password" name="pw" placeholder="Password">
	<input type="submit" name="" value="Login">
	
	<c:if test = "${usernameVorhanden == false || passwortKorrekt == false}">
		<p>Sie haben ein falsches Passwort oder einen falschen Benutzernamen eingegeben.</p><br>
	</c:if>
	<a href="kontakt.jsp">Lost your password?</a><br>	
	<a href="createAccount.jsp">Don't have an account?</a>
	</form>
</body>
</html>