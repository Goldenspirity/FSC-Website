<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>French Skyline Corporation</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/inscription/cssInscription.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css"/>
	<script src="${pageContext.request.contextPath}/inc/js/jquery.js"></script>
</head>
<body>
	<div id="blocPage">
		<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div> 
		
		<div id="form" class="center">
			<form method="post" action="<c:url value='/inscription' />">
				<fieldset>
					<div class="input">
						<label for="username">Nom d'utilisateur </label><input type="text" name="username" id="username" />
					</div>
						<span class="error">${form.errors['username']}</span>
					
					<div class="input">
						<label for="email">Email </label><input type="text" name="email" id="email" />
					</div>
						<span class="error">${form.errors['email']}</span>
					
					<div class="input">
						<label for="password">Mot de passe </label><input type="password" name="password" id="password" />
					</div>
						<span class="error">${form.errors['password']}</span> 
					
					<div class="input">
						<label for="passwordCheck">Confirmation du mot de passe </label><input type="password" name="passwordCheck" id="passwordCheck" />
					</div>
						<span class="error">${form.errors['passwordCheck']}</span> 
					
					<div class="input">
						<input type="submit" value="Inscription" id="submit" />
					</div>
				</fieldset>
			</form>
		</div>
		<p class="error">${form.errors['encryption']}</p> 
		<p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
		
		<div class="center">
			<p>Déjà enregistré ? <a href="connexion">Connectez-vous !</a></p>
		</div>
	</div>
</body>
</html>