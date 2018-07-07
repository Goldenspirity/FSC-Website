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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css" />
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
	
	<script src="${pageContext.request.contextPath}/inc/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/inc/js/newBracket.js"></script>
	<script src="${pageContext.request.contextPath}/inc/js/bracketHuitEvents.js"></script>

</head>
<body>
	<div id="blocPage">
		<div id="header">
			<c:import url="/WEB-INF/jsp/header/header.jsp"></c:import>
		</div> 
		
		<span id="backLink"><a href="${pageContext.request.contextPath}/events/tournoisInternes">Retour</a></span>
		
		<form id="globalDiv" method="post" action="<c:url value='newTournament'/>">
			<div> <!-- id="titleDateSubmit"  -->
				<input type="text" name="title" id="title" value="" />
				<input type="date" id="date" name="date" />
				<input type="submit" id="startTournament" value="Commencer le tournoi"/>
			</div>
			
			<div id="teams">
				<input type="text" name="team0" id="team0" />
			</div>
		</form>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
	</div>
</body>
</html>