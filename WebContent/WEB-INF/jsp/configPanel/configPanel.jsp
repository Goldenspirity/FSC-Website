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
	
		<h1>Panneau de configuration</h1>
		
		<div id = "members">
			<h2>Membres</h2>
			<table>
			<c:forEach items="${usersList}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>${user.role}</td>
					<td>
						<form method="post" action="config">
							<select name="updateRole">
								<option value="member_${user.name}">Membre</option>
								<option value="admin_${user.name}">Admin</option>
							</select>
							<input type="submit" value="Valider" />
						</form>
					</td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
		<div id="newsManagement">
			<h2>Ajouter une news</h2>
			
			<form method="post" action="${pageContext.request.contextPath}/config">
				<input type="text" name="title" id="title" />
				<textarea name="summary" id="summary"></textarea> <!-- Tester si le textarea marche bien dans le form -->
				<input type="submit" value="Envoyer" />
			</form>
		
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
	</div>
</body>
</html>