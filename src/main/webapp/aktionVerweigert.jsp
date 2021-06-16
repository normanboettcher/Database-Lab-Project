<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Beschreibung" content="Verweigerte Aktion">
<meta name="author" content="Thomas Mook">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="aktionVerweigertStyle.css">
<title>Aktion verweigert</title>
</head>
<body>
	<!-- Sollte Nutzer nicht eingeloggt sein, wird diese Seite präsentiert, sodass der Nutzer sich einloggen oder anmelden muss. -->
	<div class="verweigert">
		<p>Diese Aktion ist leider nicht durchführbar. Bitte Loggen Sie
			sich ein.</p>
		<a href="login.jsp"><button class="regButton" type="submit">Jetzt
				einloggen..</button></a> <br>
		<p>Noch kein Mitglied bei HOMESTAR? Join us now!</p>
		<a href="createAccount.jsp"><button class="regButton"
				type="submit">Jetzt Mitglied werden..</button></a><br> <a
			href="index.jsp"><button class="regButton" type="submit">Zurück
				zur Startseite</button></a>
	</div>
</body>
</html>