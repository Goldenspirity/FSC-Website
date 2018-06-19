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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/events/internal/cssBracket.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css" />
	<script src="${pageContext.request.contextPath}/inc/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/inc/js/newBracket.js"></script>
	<script src="${pageContext.request.contextPath}/inc/js/bracketHuitEvents.js"></script>

</head>
<body>
	<div id="blocPage">
		<div id="header">
			<c:import url="/WEB-INF/jsp/header/header.jsp"></c:import>
		</div> 
		<p><a href="<c:url value='/events/tournoisInternes'/>">Retour</a></p>
		<form id="globalDiv" method="post" action="<c:url value='newTournament'/>">
			<div id=#titleDateSubmit>
				<input type="text" name="title" id="title" value="" />
				<input type="date" id="date" name="date" />
				<input type="submit" id="startTournament" value="Commencer le tournoi"/>
			</div>
		</form>
		
		<div id="resultWindowOverlay">
			<div id="resultWindow">
				<form method="post" action="<c:url value='bracket?id=${tournament.id}'/>">
					<h5>Entrez vos résultats !</h5>
					<p><span id="team0"></span><input type='text' name="score0" id="score0"/></p>
					<p><span id="team1"></span><input type='text' name="score1" id="score1"/></p>
					<input type='submit' value='Enregistrer le résultat' id="submitResult"/>
				</form>
			</div>
		</div>
	</div>
</body>
</html>