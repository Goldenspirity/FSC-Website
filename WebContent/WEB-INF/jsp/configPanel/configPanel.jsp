<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css"/>
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
			<p>Membres</p>
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
	</div>
</body>
</html>