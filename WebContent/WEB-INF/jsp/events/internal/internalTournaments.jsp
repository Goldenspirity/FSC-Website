<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>French Sky Corporation</title>
	
	<meta charset="utf-8"/>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/fonts/cssFonts.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/general/cssGeneral.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/events/internal/cssBracket.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/header/cssHeader.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/inc/css/footer/cssFooter.css"/>
	
	<link rel="icon" type='image/png' href="${pageContext.request.contextPath}/inc/images/favicon.png" />
	
</head>
<body>
	<div id="blocPage">
		<div id="header">
			<c:import url="/WEB-INF/jsp/header/header.jsp"></c:import>
		</div> 
		
		<div>
			<c:if test="${sessionScope.user != null && (sessionScope.user.role == 'organizer' || sessionScope.user.role == 'admin' || sessionScope.user.role == 'superadmin')}">
				<p><a href="<c:url value='tournoisInternes/newTournament'/>">Nouveau tournoi à huit équipes.</a></p>
			</c:if>
			<p>Tournois précédents :</p>
			<table>
			<c:forEach items="${tournamentsList}" var="tournament">
				<tr><td>${tournament.id}</td><td>${tournament.dateTournament}</td>
				    <td><a href="<c:url value='tournoisInternes/bracket?id=${tournament.id}'/>">${tournament.title}</a></td>
				    <td><a href="<c:url value='tournoisInternes/removeTournament'><c:param name='id' value='${tournament.id}'/></c:url>">Supprimer</a></td></tr>
	
			</c:forEach>
			</table>
		</div>
		
		<div id="footer"> <c:import url="/WEB-INF/jsp/footer/footer.jsp"></c:import> </div> 
	</div>
</body>
</html>