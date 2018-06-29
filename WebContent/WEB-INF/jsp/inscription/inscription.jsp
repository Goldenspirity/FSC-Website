<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Inscription - French Skyline Corporation</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/inscription/cssInscription.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css"/>
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
</head>
<body>
	<div id="blocPage">
		<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div> 
		
		<div id="inscription">
			<div id="form">
				<form method="post" action="<c:url value='/inscription' />">
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
						<label for="passwordCheck">Confirmation du <br/>mot de passe </label><input type="password" name="passwordCheck" id="passwordCheck" />
					</div>
						<span class="error">${form.errors['passwordCheck']}</span> 
					
					<div class="input submitInput">
						<input type="submit" value="Inscription" id="submit" />
					</div>
				</form>
			</div>
			<c:if test="${form.errors != null}">
				<c:if test="${form.errors['encryption'] != null}"><p class="error">${form.errors['encryption']}</p></c:if> 
				<p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
			</c:if>
			
			<div id="alreadyRegistered">
				<p>Déjà enregistré ? <a href="connexion">Connectez-vous !</a></p>
			</div>
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
	</div>
</body>
</html>