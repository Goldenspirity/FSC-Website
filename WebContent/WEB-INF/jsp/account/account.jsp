<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>French Sky Corporation</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/account/cssAccount.css"/>
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
	
</head>
<body>
	<div id="blocPage">
		<div id="header"> <c:import url="/WEB-INF/jsp/header/header.jsp"></c:import> </div> 
		
		<div id="accountManagement">
			<h1>Salut ${user.name} !</h1>
			
			<h3>Tu es inscrit depuis le ${user.registerDate}.</h3>
			
			<h2>Changer ton mot de passe ?</h2>
			<div id="passwordUpdate" class="update">
				<form method="post" action="account">
					<div class="labelInput">
						<label for="oldPassword">Ancien mot de passe : </label>
						<input type="password" name="oldPassword" />
					</div>
					<span class="error">${form.errors['oldPassword']}</span>
					<br />
					<div class="labelInput">
						<label for="newPassword">Nouveau mot de passe : </label>
						<input type="password" name="newPassword" />
					</div>
					<span class="error">${form.errors['newPassword']}</span>
					<br />
					<div class="labelInput">
						<label for="newPasswordCheck">Confirmation : </label>
						<input type="password" name="newPasswordCheck" />
					</div>
					<span class="error">${form.errors['newPasswordCheck']}</span>
					<br />
					<input type="submit" class="submitButton" value="Changer de mot de passe" />
				</form>
			</div>
			
			<h2>Changer ton adresse mail ?</h2>
			<div id="emailUpdate" class="update">
				<form method="post" action="account">
					<div class="labelInput">
						<label for="email">Nouvelle adresse mail : </label>
						<input type="text" name="email" />
					</div>
					<span class="error">${form.errors['email']}</span>
					<br />
					<div class="labelInput">
						<label for="password">Mot de passe : </label>
						<input type="password" name="password" />
					</div>
					<span class="error">${form.errors['password']}</span>
					<br />
					<input type="submit" class="submitButton" value="Changer d'adresse mail" />
				</form>
			</div>
			<span class="${empty form.errors ? 'succes' : 'error'}">${form.result}</span>
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
	</div>
</body>
</html>