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
	<script src="${pageContext.request.contextPath}/inc/js/bracketHuitShape.js"></script>
	<script src="${pageContext.request.contextPath}/inc/js/bracketHuitEvents.js"></script>
	<script>
		jQuery(document).ready(function(){
			$("#title").html("${tournament.title}");
			$("#date").html("${tournament.dateTournament}");
			
			<c:forEach var="spot" items="${tournament.spots}">
					$("#t${spot.key}").html("<span>${spot.value}</span>");
			</c:forEach>
			
			<c:forEach var="score" items="${tournament.scores}">
				<c:if test="${score.value[0] != -1 && score.value[1] != -1}">
					$("#sc${score.key*2}").html("<span>${score.value[0]}</span>");
					$("#sc${score.key*2+1}").html("<span>${score.value[1]}</span>");
				</c:if>
			</c:forEach>
		

		});
	</script>
</head>
<body>
	<div id="blocPage">
		<div id="header">
			<c:import url="/WEB-INF/jsp/header/header.jsp"></c:import>
		</div> 
		
		<div id="globalDiv" class="globalDiv">
			<div>
			<h1 id="title">Title</h1>
			<p id="date">Date</p>
			</div>
		
		
		</div>
		
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