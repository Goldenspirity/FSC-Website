<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sdzee.beans.Tournament" %>
<!DOCTYPE html>
<html>
<head>
	<title>French Skyline Corporation</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/events/internal/cssNewTournament.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css" />
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
</head>
<body>
	<div id="blocPage">
		<div id="header">
			<c:import url="/WEB-INF/jsp/header/header.jsp"></c:import>
		</div> 
		
		<div id="globalDiv">
		
			<span id="backLink"><a href="${pageContext.request.contextPath}/events/tournoisInternes">Retour</a></span>
			
			<form id="form" method="post" action="<c:url value='newTournament'/>">
				<div id="titleDateSubmit">
					<div id="submitButton">
						<input type="submit" id="startTournament" class="titleDateSubmit" value="Commencer le tournoi"/>
					</div>
					
					<div id="titleDate">
						<input type="text" name="title" id="title" class="titleDateSubmit" value="" />
						<input type="date" id="date" name="date" class="titleDateSubmit" />
					</div>
				</div>
				<div id="allTeams">
					<div id="teams1">
						<div class="labelInput">
							<label for="team0">Equipe 1 </label><input type="text" name="team0" id="team0" />
						</div>
					</div>
				</div>
			</form>
			
			<div id="AddOneTeamButton">
				<img src="${pageContext.request.contextPath}/inc/images/plus.png" />
			</div>
		</div>
			
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 

	</div>
	
	<script src="${pageContext.request.contextPath}/inc/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/inc/js/events/newTournament.js"></script>
</body>
</html>