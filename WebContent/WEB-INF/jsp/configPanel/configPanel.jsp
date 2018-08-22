<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/configPanel/cssConfigPanel.css"/>
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
	<title>French Skyline Corporation</title>
</head>
<body>
	<div id="blocPage">
		<div id="header">
			<c:import url="/WEB-INF/jsp/header/header.jsp"></c:import>
		</div> 
		
		<div id="content">
		
			<h1>Panneau de configuration</h1>
			
			<div id="members" class="contentDiv">
				<h2>Membres</h2>
				<table>
				<c:forEach items="${usersList}" var="user">
					<c:if test="${user.name != 'admin'}">
						<tr>
							<td class="cell">${user.name}</td>
							<td class="cell">
								<c:choose>
									<c:when test="${user.role == 'admin'}">
										Administrateur
									</c:when>
									<c:when test="${user.role == 'guest'}">
										Invité
									</c:when>
									<c:when test="${user.role == 'organizer'}">
										Animateur
									</c:when>
									<c:when test="${user.role == 'player'}">
										Joueur
									</c:when>
									<c:when test="${user.role == 'captain'}">
										Capitaine
									</c:when>
								</c:choose>
							</td>
							<td>
								<form method="post" action="config">
									<select name="updateRole">
										<option value="guest/${user.name}">Invité</option>
										<option value="player/${user.name}">Joueur</option>
										<option value="captain/${user.name}">Capitaine</option>
										<option value="organizer/${user.name}">Animateur</option>
										<option value="admin/${user.name}">Admin</option>
									</select>
									<input type="submit" id="userSubmit" value="Valider" />
								</form>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				</table>
			</div>
			
			<div id="newsManagement" class="contentDiv">
				<h2>Ajouter une news</h2>
				
				<form method="post" action="${pageContext.request.contextPath}/config">
					<label for="title">Titre (~ 50 caractères)</label><input type="text" name="title" id="title" />
					<br/>
					<label for="imageUrl">Url de l'image</label><input type="text" name="imageUrl" id="imageUrl" />
					<br/>
					<label for="summary">Résumé (~ 300 caractères)</label><textarea name="summary" id="summary"></textarea>
					<br />
					<label for="content">Contenu</label><textarea name="newsContent" id="newsContent"></textarea>
					<br />
					<input type="submit" value="Envoyer" />
				</form>
			
			</div>
		</div>
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
	</div>
</body>
</html>